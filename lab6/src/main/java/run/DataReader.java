package run;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class DataReader {
    private static final String FIRST_GREETING = "Введите название первого города: ";
    private static final String SECOND_GREETING = "Введите название второго города: ";
    private static final String TIME_GREETING = "Введите время пути между городами в днях: ";
    private static final String NUMBER_GREETING = "Введите количество маршрутов: ";
    private static final double EPSILON = 0.001;

    private DataReader() {
    }

    public static double[][] readData(@NotNull BufferedReader reader, boolean silent) throws IOException {
        var cities = new TreeSet<String>();
        var ways = new TreeMap<String, Map<String, Double>>();
        if (!silent) {
            System.out.print(NUMBER_GREETING);
        }
        var cnt = Double.parseDouble(reader.readLine().strip());
        for (var i = 0; i < cnt; ++i) {
            if (!silent) {
                System.out.print(FIRST_GREETING);
            }
            var first = reader.readLine().strip();
            if (first.isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (!silent) {
                System.out.print(SECOND_GREETING);
            }
            var second = reader.readLine().strip();
            if (second.isEmpty() || first.equals(second)) {
                throw new IllegalArgumentException();
            }
            if (!silent) {
                System.out.print(TIME_GREETING);
            }
            var time = Double.parseDouble(reader.readLine().strip());
            if (time < EPSILON) {
                throw new IllegalArgumentException();
            }
            cities.add(first);
            cities.add(second);
            if (!ways.containsKey(first)) {
                ways.put(first, new TreeMap<>());
            } else if (ways.get(first).containsKey(second)) {
                throw new IllegalArgumentException();
            }
            ways.get(first).put(second, time);
            if (!ways.containsKey(second)) {
                ways.put(second, new TreeMap<>());
            }
            ways.get(second).put(first, time);
        }
        var size = cities.size();
        var list = new ArrayList<>(cities);
        var result = new double[size][size];
        for (var i = 0; i < size; ++i) {
            result[i][i] = -1;
            var first = list.get(i);
            if (ways.containsKey(first)) {
                for (var j = 0; j < i; ++j) {
                    result[i][j] = ways.get(first).getOrDefault(list.get(j), -1.0);
                    result[j][i] = result[i][j];
                }
            }
        }
        return result;
    }
}
