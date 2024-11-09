package files;

import org.jetbrains.annotations.NotNull;
import text.TextSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesUtil {
    private final File root;

    public FilesUtil(String path) throws IOException {
        root = new File(path);
        if (root.exists()) {
            clear();
        }
        Files.createDirectories(root.toPath());
    }

    public void save(@NotNull TextSource source, @NotNull Path path) throws IOException {
        try (var out = Files.newBufferedWriter(path)) {
            source.get().forEach(line -> {
                try {
                    out.write(line);
                    out.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public Path assemblePath(@NotNull String ...parts) throws IOException {
        var path = root.getPath();
        for (var i = 0; i < parts.length - 1; ++i) {
            path = path + File.separator + parts[i];
            var file = new File(path);
            try {
                Files.createDirectory(file.toPath());
            } catch (IOException ignored) {
            }
        }
        if (parts.length > 0) {
            path = path + File.separator + parts[parts.length - 1] + ".html";
        }
        return Paths.get(path);
    }

    public void remove(@NotNull Path path) throws IOException {
        Files.delete(path);
    }

    public void removeEmpty() throws IOException {
        Files.walkFileTree(root.toPath(), DeleteEmptyDirectoriesVisitor.getInstance());
    }

    private void clear() throws IOException {
        Files.walkFileTree(root.toPath(), DeleteFileVisitor.getInstance());
    }
}
