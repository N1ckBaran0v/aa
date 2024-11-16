package threads;

import org.jetbrains.annotations.NotNull;
import tasks.Task;
import files.SearchFileVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;

public final class TasksCreator extends AbstractPipeRunnable {
    public TasksCreator(@NotNull Queue<Task> output, @NotNull Path logPath) throws IOException {
        super(null, output, logPath);
    }

    @Override
    public void run() {
        try (var log = Files.newBufferedWriter(logPath)) {
            Files.walkFileTree(Paths.get("input"), new SearchFileVisitor(output, log));
            synchronized (output) {
                output.add(Task.getTerminator());
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
