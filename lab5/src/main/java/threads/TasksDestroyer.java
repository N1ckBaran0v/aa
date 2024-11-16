package threads;

import org.jetbrains.annotations.NotNull;
import tasks.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public final class TasksDestroyer extends AbstractPipeRunnable {
    private final Semaphore semaphore;

    public TasksDestroyer(@NotNull Queue<Task> input, @NotNull Path logPath, @NotNull Semaphore semaphore) throws
            Exception {
        super(input, null, logPath);
        this.semaphore = semaphore;
        semaphore.acquire();
    }

    @Override
    public void run() {
        try (var log = Files.newBufferedWriter(logPath)) {
            var task = (Task) null;
            while (task != Task.getTerminator()) {
                synchronized (input) {
                    if (!input.isEmpty()) {
                        task = input.remove();
                        if (task != Task.getTerminator()) {
                            task.destroy(log);
                        }
                    }
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } finally {
            semaphore.release();
        }
    }
}
