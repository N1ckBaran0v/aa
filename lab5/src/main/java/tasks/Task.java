package tasks;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.IOException;

public final class Task {
    private boolean destroyed = false;
    private static long counter = 0;
    private static final Task terminator = new Task();
    private final long id;
    private Object data;

    public Task(@NotNull BufferedWriter log) throws IOException {
        id = ++counter;
        log.write(String.format("%.9f %d created\n", System.nanoTime() / 1e9, id));
    }

    private Task() {
        id = -1;
    }

    public static Task getTerminator() {
        return terminator;
    }

    public long getId() {
        if (destroyed) {
            throw new TaskDestroyedException();
        }
        return id;
    }

    public void destroy(@NotNull BufferedWriter log) throws IOException {
        destroyed = true;
        log.write(String.format("%.9f %d destroyed\n", System.nanoTime() / 1e9, id));
    }

    public Object getData() {
        if (destroyed) {
            throw new TaskDestroyedException();
        }
        return data;
    }

    public void setData(Object data) {
        if (destroyed) {
            throw new TaskDestroyedException();
        }
        this.data = data;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setCounter(long counter) {
        Task.counter = counter;
    }
}
