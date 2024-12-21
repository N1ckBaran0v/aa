package run;

import algorithms.AntsAlgorithm;
import algorithms.CompleteSearchAlgorithm;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solver {
    private static final int REPEATS = 10;

    private Solver() {
    }

    public static void solve(double @NotNull [] @NotNull [] ...matrices) throws IOException {
        var real = new CompleteSearchAlgorithm();
        var answers = new double[matrices.length];
        for (var i = 0; i < matrices.length; ++i) {
            answers[i] = real.solve(matrices[i]);
            System.out.printf("Ответ для класса данных %d: %.3f\n", i + 1, answers[i]);
        }
        try (var writer = Files.newBufferedWriter(Path.of("result.log"))) {
            var days = new int[]{5, 10, 50, 100, 500};
            for (var alpha = 0.1; alpha < 1; alpha += 0.2) {
                for (var rho = 0.1; rho < 1; rho += 0.2) {
                    for (var day : days) {
                        var ants = new AntsAlgorithm(alpha, rho, day);
                        var maxvals = new double[matrices.length];
                        var minvals = new double[matrices.length];
                        var sums = new double[matrices.length];
                        writer.write(String.format("%.1f & %.1f & %d ", alpha, rho, day));
                        for (var i = 0; i < matrices.length; ++i) {
                            for (var j = 0; j < REPEATS; ++j) {
                                var curr = ants.solve(matrices[i]);
                                var diff = Math.abs(answers[i] - curr);
                                if (j == 0 || maxvals[i] < diff) {
                                    maxvals[i] = diff;
                                }
                                if (j == 0 || minvals[i] > diff) {
                                    minvals[i] = diff;
                                }
                                sums[i] += diff;
                            }
                            writer.write(String.format("& %.3f & %.3f & %.3f ", maxvals[i],
                                    (maxvals[i] + minvals[i]) / 2, sums[i] / REPEATS));
                        }
                        writer.write("\\\\");
                        writer.newLine();
                        writer.write("\\hline");
                        writer.newLine();
                    }
                }
            }
        }

    }
}
