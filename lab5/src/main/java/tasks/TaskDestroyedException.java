package tasks;

public final class TaskDestroyedException extends RuntimeException {
    public TaskDestroyedException() {
        super("Task has been destroyed");
    }
}
