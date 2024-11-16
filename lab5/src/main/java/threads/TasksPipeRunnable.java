package threads;

import org.jetbrains.annotations.NotNull;
import tasks.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Queue;

public abstract class TasksPipeRunnable extends AbstractPipeRunnable {
    private final String action;

    protected TasksPipeRunnable(@NotNull Queue<Task> input, @NotNull Queue<Task> output, @NotNull Path logPath,
                                @NotNull String action) {
        super(input, output, logPath);
        this.action = action;
    }

    @Override
    public final void run() {
        try (var log = Files.newBufferedWriter(logPath)) {
            var task = (Task) null;
            while (task != Task.getTerminator()) {
                synchronized (input) {
                    if (!input.isEmpty()) {
                        task = input.remove();
                        if (task != Task.getTerminator()) {
                            log.write(String.format("%.9f %d start\\_%s\n", System.nanoTime() / 1e9, task.getId(),
                                    action));
                        }
                    }
                }
                if (task != null) {
                    if (task != Task.getTerminator()) {
                        taskAction(task);
                        log.write(String.format("%.9f %d end\\_%s\n", System.nanoTime() / 1e9, task.getId(),
                                action));
                    }
                    synchronized (output) {
                        output.add(task);
                    }
                    if (task != Task.getTerminator()) {
                        task = null;
                    }
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected abstract void taskAction(Task task);
}
