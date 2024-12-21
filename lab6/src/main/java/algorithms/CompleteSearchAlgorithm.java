package algorithms;

import org.jetbrains.annotations.NotNull;

public class CompleteSearchAlgorithm extends Algorithm {
    @Override
    public double solve(double @NotNull [] @NotNull [] matrix) {
        var result = super.solve(matrix);
        var buffer = new int[matrix.length];
        var used = new boolean[matrix.length];
        var sum = .0;
        var current = 0;
        var index = 0;
        var up = false;
        while (index >= 0) {
            if (up) {
                if (index + 1 == matrix.length) {
                    if (matrix[current][0] != -1) {
                        sum += matrix[current][0];
                        if (sum < DAYS + EPSILON && (result == -1 || sum < result)) {
                            result = sum;
                        }
                        sum -= matrix[current][0];
                    }
                    buffer[index] = matrix.length - 1;
                } else {
                    buffer[index] = 0;
                }
                up = false;
            } else {
                ++buffer[index];
                if (buffer[index] == matrix.length) {
                    --index;
                    used[current] = false;
                    if (index < 1) {
                        current = 0;
                        sum = 0;
                    } else {
                        current = buffer[index - 1];
                        sum -= matrix[current][buffer[index]];
                    }
                } else if (matrix[current][buffer[index]] != -1 && !used[buffer[index]]) {
                    sum += matrix[current][buffer[index]];
                    if (sum < DAYS + EPSILON) {
                        used[buffer[index]] = true;
                        current = buffer[index];
                        up = true;
                        ++index;
                    } else {
                        sum -= matrix[current][buffer[index]];
                    }
                }
            }
        }
        return result;
    }
}
