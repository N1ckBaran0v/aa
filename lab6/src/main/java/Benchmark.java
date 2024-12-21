import algorithms.Algorithm;
import algorithms.AntsAlgorithm;
import algorithms.CompleteSearchAlgorithm;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

public class Benchmark {
    private static final int REPEAT_COUNT = 100;

    private Benchmark() {
    }

    public static void main(String[] args) {
        var threadMXBean = ManagementFactory.getThreadMXBean();
        var algorithms = new TreeMap<String, Algorithm>();
        algorithms.put("Метод полного перебора", new CompleteSearchAlgorithm());
        algorithms.put("Муравьиный алгоритм", new AntsAlgorithm(0.1, 0.1, 50));
        for (var name : algorithms.keySet()) {
            var algorithm = algorithms.get(name);
            System.out.println(name);
            var times = new double[5];
            for (var size = 2; size < 12; ++size) {
                var index = (size >> 1) - 1;
                for (var i = 0; i < REPEAT_COUNT; ++i) {
                    var field = generateField(size);
                    var start = threadMXBean.getCurrentThreadCpuTime();
                    algorithm.solve(field);
                    var end = threadMXBean.getCurrentThreadCpuTime();
                    times[index] += end - start;
                }
                times[index] /= REPEAT_COUNT * 1e9;
            }
            System.out.println(Arrays.toString(times));
        }
    }

    private static double[][] generateField(int size) {
        double[][] field = new double[size][size];
        for (var doubles : field) {
            Arrays.fill(doubles, -1);
        }
        var gen = new Random();
        var links = gen.nextInt(maxLinks(size) + 1);
        for (var i = 0; i < links; ++i) {
            var first = gen.nextInt(size);
            var second = gen.nextInt(size);
            if (first == second) {
                --i;
            } else {
                field[first][second] = field[second][first] = 1;
            }
        }
        return field;
    }

    private static int maxLinks(int size) {
        var result = 1;
        for (var i = 2; i <= size; ++i) {
            result *= i;
        }
        return result >> 1;
    }
}
