package threads;

import org.jetbrains.annotations.NotNull;
import tasks.Task;

import java.nio.file.Path;
import java.util.Queue;

public abstract class AbstractPipeRunnable implements Runnable {
    protected final Queue<Task> input;
    protected final Queue<Task> output;
    protected final Path logPath;

    protected AbstractPipeRunnable(Queue<Task> input, Queue<Task> output, @NotNull Path logPath) {
        this.input = input;
        this.output = output;
        this.logPath = logPath;
    }
}
