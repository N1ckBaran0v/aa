package algorithms;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class AntsAlgorithm extends Algorithm {
    private final double alpha, beta, rho;
    private final int days;

    public AntsAlgorithm(double alpha, double rho, int days) {
        if (alpha > 1 || alpha < 0) {
            throw new IllegalArgumentException("alpha must be between 0 and 1");
        }
        if (rho > 1 || rho < 0) {
            throw new IllegalArgumentException("rho must be between 0 and 1");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("days must be positive");
        }
        this.alpha = alpha;
        this.beta = 1 - alpha;
        this.rho = 1 - rho;
        this.days = days;
    }

    @Override
    public double solve(double @NotNull [] @NotNull [] matrix) {
        var result = super.solve(matrix);
        var visibility = new double[matrix.length][matrix.length];
        var results = new double[matrix.length];
        var routes = new int[matrix.length][matrix.length];
        var lengths = new int[matrix.length];
        var delta = new double[matrix.length][matrix.length];
        var pheromones = new double[matrix.length][matrix.length];
        var multi = new double[matrix.length][matrix.length];
        var ways = new int[matrix.length];
        var used = new boolean[matrix.length];
        var quote = .0;
        for (var i = 0; i < matrix.length; ++i) {
            for (var j = 0; j < matrix[i].length; ++j) {
                pheromones[i][j] = EPSILON;
                visibility[i][j] = 1 / matrix[i][j];
                quote += matrix[i][j];
            }
        }
        quote /= (matrix.length - 1) << 1;
        for (var day = 0; day < days; ++day) {
            // Morning
            for (var i = 0; i < matrix.length; ++i) {
                results[i] = 0;
                lengths[i] = 0;
                for (var j = 0; j < matrix.length; ++j) {
                    delta[i][j] = 0;
                    multi[i][j] = Math.pow(pheromones[i][j], alpha) * Math.pow(visibility[i][j], beta);
                }
            }
            // Day
            for (var ant = 0; ant < matrix.length; ++ant) {
                Arrays.fill(used, false);
                var flag = true;
                var curr = ant;
                while (flag) {
                    if (lengths[ant] + 1 == matrix.length) {
                        if (matrix[curr][ant] != -1 && results[ant] + matrix[curr][ant] < DAYS + EPSILON) {
                            results[ant] += matrix[curr][ant];
                            routes[ant][lengths[ant]++] = ant;
                        }
                        flag = false;
                    } else {
                        var count = 0;
                        var sum = .0;
                        for (var i = 0; i < matrix.length; ++i) {
                            if (matrix[curr][i] != -1 && !used[i] && i != ant) {
                                ways[count++] = i;
                                sum += multi[curr][i];
                            }
                        }
                        if (count > 0) {
                            var decision = Math.random() * sum;
                            while (decision < sum) {
                                decision += multi[curr][ways[--count]];
                            }
                            if (results[ant] + matrix[curr][ways[count]] < DAYS + EPSILON) {
                                results[ant] += matrix[curr][ways[count]];
                                curr = ways[count];
                                routes[ant][lengths[ant]++] = curr;
                                used[curr] = true;
                            } else {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }
            // Evening
            for (var ant = 0; ant < matrix.length; ++ant) {
                if (lengths[ant] == matrix.length && (result == -1 || results[ant] < result)) {
                    result = results[ant];
                }
            }
            // Night
            for (var ant = 0; ant < matrix.length; ++ant) {
                if (lengths[ant] > 0) {
                    var antDelta = quote / results[ant];
                    for (var i = 0; i < lengths[ant] - 1; ++i) {
                        delta[routes[ant][i]][routes[ant][i + 1]] += antDelta;
                        delta[routes[ant][i + 1]][routes[ant][i]] += antDelta;
                    }
                    if (lengths[ant] == matrix.length) {
                        delta[routes[ant][matrix.length - 1]][routes[ant][0]] += antDelta;
                        delta[routes[ant][0]][routes[ant][matrix.length - 1]] += antDelta;
                    }
                }
            }
            for (var i = 1; i < matrix.length; ++i) {
                for (var j = 0; j < i; ++j) {
                    pheromones[i][j] = delta[i][j] + rho * pheromones[i][j];
                    if (pheromones[i][j] < EPSILON) {
                        pheromones[i][j] = EPSILON;
                    }
                    pheromones[j][i] = pheromones[i][j];
                }
            }
        }
        return result;
    }
}
