package files;

import org.jetbrains.annotations.NotNull;
import tasks.Task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Queue;

public final class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private final Queue<Task> output;
    private final BufferedWriter log;

    public SearchFileVisitor(@NotNull Queue<Task> output, @NotNull BufferedWriter log) {
        this.output = output;
        this.log = log;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var task = new Task(log);
        task.setData(file);
        synchronized (output) {
            output.add(task);
        }
        return FileVisitResult.CONTINUE;
    }
}
