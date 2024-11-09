package work;

import org.jetbrains.annotations.NotNull;
import org.jsoup.HttpStatusException;
import text.PageLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class ParallelTask implements Runnable {
    private final Context context;
    private final int number;
    private static final Pattern pattern = Pattern.compile("href=\"/[^\"?.]+(\"|[?])");
    private static final Pattern recipe = Pattern.compile("/recepty/.+/.+");

    public ParallelTask(@NotNull Context context, int number) {
        this.context = context;
        this.number = number;
        try {
            context.getSemaphores().get(number).acquire();
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                var path = getPath();
                if (path == null) {
                    break;
                }
                var filepath = getFilepath(path);
                var loader = new PageLoader(path);
                context.getFilesUtil().save(loader, filepath);
                var links = getLinks(filepath);
                saveLinks(links);
                if (!recipe.matcher(path).matches()) {
                    context.getFilesUtil().remove(filepath);
                }
            } catch (IOException | InterruptedException ignored) {
            }
        }
        context.getSemaphores().get(number).release();
    }

    private Path getFilepath(String path) throws IOException {
        var filename = path;
        if ("/".equals(path)) {
            filename = "/index";
        }
        var parts = filename.substring(1).split("/");
        return context.getFilesUtil().assemblePath(parts);
    }

    private Queue<String> getLinks(Path path) throws IOException {
        var matched = new LinkedList<String>();
        try (var in = Files.newBufferedReader(path)) {
            in.lines().forEach(line -> {
                var match = pattern.matcher(line);
                while (match.find()) {
                    matched.add(line.substring(match.start() + 6, match.end() - 1));
                }
            });
        }
        return matched;
    }

    private void saveLinks(Queue<String> links) throws InterruptedException {
        synchronized (context) {
            links.forEach(link -> {
                if (!context.getAll().contains(link)) {
                    context.getAll().add(link);
                    var match = recipe.matcher(link);
                    if (match.find()) {
                        context.getPriority().offer(link);
                    } else {
                        context.getQueue().offer(link);
                    }
                }
            });
        }
    }

    private String getPath() throws InterruptedException {
        var path = (String) null;
        context.getReaders().acquire();
        var flag = false;
        while (path == null && !flag) {
            synchronized (context) {
                if (context.getMaxCount() == context.getUsed().size()) {
                    flag = true;//break;
                }
                else {
                    if (context.getQueue().size() + context.getPriority().size() > 0) {
                        path = context.getPriority().isEmpty() ? context.getQueue().remove() : context.getPriority().remove();
                        if (recipe.matcher(path).matches()) {
                            context.getUsed().add(path);
                        }
                        context.getReaders().release();
                    } else if (context.getReaders().availablePermits() == 0) {
                        flag = true;//break;
                    }
                }
            }
        }
        return path;
    }
}
