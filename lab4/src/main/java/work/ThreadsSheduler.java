package work;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ThreadsSheduler {
    private ThreadsSheduler() {
    }

    public static void start(@NotNull String dir, int pages, int threads) throws IOException {
        if (pages < 1 || threads < 0) {
            throw new IllegalArgumentException();
        }
        var ctx = new Context(dir, pages, threads);
        if (threads > 0) {
            for (var i = 0; i < threads; ++i) {
                var thread = new Thread(new ParallelTask(ctx, i));
                thread.start();
            }
            for (var i = 0; i < threads; ++i) {
                try {
                    ctx.getSemaphores().get(i).acquire();
                } catch (InterruptedException ignored) {
                }
            }
        } else {
            new ParallelTask(ctx, 0).run();
        }
    }
}
