package algorithms;

import org.jetbrains.annotations.NotNull;

public abstract class Algorithm {
    static final double EPSILON = 1e-6;
    static final int DAYS = 80;

    public double solve(double @NotNull [] @NotNull [] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException();
        }
        for (var i = 0; i < matrix.length; ++i) {
            if (matrix[i].length != matrix.length) {
                throw new IllegalArgumentException();
            }
            if (matrix[i][i] != -1) {
                throw new IllegalArgumentException();
            }
            for (var j = 0; j < i; ++j) {
                if (matrix[j][i] != matrix[i][j] || (matrix[j][i] != -1 && matrix[i][j] < EPSILON)) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return -1;
    }
}
