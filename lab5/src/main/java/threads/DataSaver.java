package threads;

import database.DatabaseService;
import database.Recipe;
import org.jetbrains.annotations.NotNull;
import tasks.Task;

import java.nio.file.Path;
import java.util.Queue;

public final class DataSaver extends TasksPipeRunnable {
    private final DatabaseService databaseService;

    public DataSaver(@NotNull Queue<Task> input, @NotNull Queue<Task> output, @NotNull Path logPath,
                     @NotNull String action, @NotNull DatabaseService databaseService) {
        super(input, output, logPath, action);
        this.databaseService = databaseService;
    }

    @Override
    protected void taskAction(Task task) {
        databaseService.addRecipe((Recipe) task.getData());
    }
}
