import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

public final class Benchmark {
    private static final String PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    private static final Gson GSON = new Gson();

    private Benchmark() {
    }

    public static void benchmark() {
        var x = 8081;
        var size = x / 8 + ((x >> 2) % 10 == 0 ? x % 1000 : ((x >> 2) % 10 * (x % 10) + (x >> 1) % 10));
        var array = Array.generateArray(size, true);
        var first = getDate(array, array::search);
        saveData("linear.json", first);
        var second = getDate(array, array::binarySearch);
        saveData("binary.json", second);
        second.sort(Comparator.comparing(a -> a.get(1)));
        saveData("sorted.json", second);
    }

    private static List<List<Integer>> getDate(Array array, UnaryOperator<Integer> operator) {
        var list = new ArrayList<List<Integer>>();
        for (var i = 0; i <= array.size(); ++i) {
            var element = new ArrayList<Integer>();
            element.add(i);
            element.add(operator.apply(i));
            list.add(element);
        }
        return list;
    }

    private static void saveData(String filename, List<List<Integer>> data) {
        try (var writer = GSON.newJsonWriter(Files.newBufferedWriter(Paths.get(PATH + filename)))) {
            GSON.toJson(data, List.class, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
