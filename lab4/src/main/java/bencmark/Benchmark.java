package bencmark;

import work.ThreadsSheduler;

import java.io.IOException;

public class Benchmark {
    private static final int[] COUNTS = {0, 1, 2, 4, 8, 16};
    private static final int PAGES = 1000;

    private Benchmark() {
    }

    public static void benchmark() throws IOException {
        System.out.println("{");
        for (var count : COUNTS) {
            var start = System.nanoTime();
            ThreadsSheduler.start("benchmark", PAGES, count);
            var end = System.nanoTime();
            System.out.printf("\t%d: %.6f,\n", count, 60 / ((end - start) / (1e9 * PAGES)));
            System.out.flush();
        }
        System.out.println("}");
    }
}
