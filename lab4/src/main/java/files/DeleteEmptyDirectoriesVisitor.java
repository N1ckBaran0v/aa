package files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public class DeleteEmptyDirectoriesVisitor extends SimpleFileVisitor<Path> {
    private static final SimpleFileVisitor<Path> INSTANCE = new DeleteEmptyDirectoriesVisitor();

    private DeleteEmptyDirectoriesVisitor() {
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        try {
            Files.delete(dir);
        } catch (IOException ignored) {
        }
        return FileVisitResult.CONTINUE;
    }

    public static SimpleFileVisitor<Path> getInstance() {
        return INSTANCE;
    }
}
