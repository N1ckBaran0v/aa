package threads;

import org.jetbrains.annotations.NotNull;
import tasks.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Queue;

public final class FileReader extends TasksPipeRunnable {
    public FileReader(@NotNull Queue<Task> input, @NotNull Queue<Task> output, @NotNull Path logPath,
                      @NotNull String action) {
        super(input, output, logPath, action);
    }

    @Override
    protected void taskAction(Task task) {
        var data = new StringBuilder();
        try (var reader = Files.newBufferedReader((Path) task.getData())) {
            reader.lines().forEach(data::append);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        task.setData(data.toString());
    }
}
