package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class LogsMerger {
    private LogsMerger() {
    }

    public static void mergeLogs(Path ...paths) throws IOException {
        var lines = new ArrayList<String[]>();
        var tasks = new LinkedHashMap<String, List<String[]>>();
        getData(lines, tasks, paths);
        merge(lines);
        getStats(tasks);
    }

    private static void getData(List<String[]> lines, Map<String, List<String[]>> tasks, Path ...paths)
            throws IOException {
        for (Path path : paths) {
            try (var reader = Files.newBufferedReader(path)) {
                reader.lines().forEach(line -> {
                    var tmp = line.split(" ");
                    lines.add(tmp);
                    if (!tasks.containsKey(tmp[1])) {
                        tasks.put(tmp[1], new ArrayList<>());
                    }
                    tasks.get(tmp[1]).add(tmp);
                });
            }
            Files.delete(path);
        }
    }

    private static void merge(List<String[]> lines) throws IOException {
        lines.sort(Comparator.comparing(a -> a[0]));
        try (var log = Files.newBufferedWriter(Path.of("output", "final.log"))) {
            var num = 0;
            for (var line : lines) {
                log.write(String.format("%d & %s & %s & $%s$\\\\\n\\hline\n", ++num, line[0], line[1], line[2]));
            }
        }
    }

    private static void getStats(Map<String, List<String[]>> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Задачи не были созданы");
            return;
        }
        var queuesWaiting = (double[]) null;
        var worktime = .0;
        var stageTimes = new LinkedHashMap<String, Double>();
        var records = 0;
        var queues = 0;
        for (var value : tasks.values()) {
            value.sort(Comparator.comparing(a -> a[0]));
            if (records == 0) {
                records = value.size();
                queues = records >> 1;
                queuesWaiting = new double[queues];
            }
            var times = new double[records];
            for (var i = 0; i < records; ++i) {
                times[i] = Double.parseDouble(value.get(i)[0]);
            }
            for (var i = 0; i < records; i += 2) {
                queuesWaiting[i >> 1] += times[i + 1] - times[i];
                if (i > 0) {
                    var stage = value.get(i)[2].split("_")[1];
                    stageTimes.put(stage, stageTimes.getOrDefault(stage, .0) + times[i] - times[i - 1]);
                }
            }
            worktime += times[records - 1] - times[0];
        }
        System.out.printf("Среднее время существования задачи: %.9f\n", worktime / tasks.size());
        for (var i = 0; i < queues; ++i) {
            System.out.printf("Среднее время ожидания в очереди %d: %.9f\n", i + 1, queuesWaiting[i] / tasks.size());
        }
        for (var stage : stageTimes.keySet()) {
            System.out.printf("Среднее время выполнения этапа %s: %.9f\n", stage, stageTimes.get(stage) / tasks.size());
        }
    }
}
