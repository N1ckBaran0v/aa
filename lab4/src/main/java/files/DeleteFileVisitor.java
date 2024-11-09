package files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public final class DeleteFileVisitor extends SimpleFileVisitor<Path> {
    private static final SimpleFileVisitor<Path> INSTANCE = new DeleteFileVisitor();

    private DeleteFileVisitor() {
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        Files.delete(dir);
        return FileVisitResult.CONTINUE;
    }

    public static SimpleFileVisitor<Path> getInstance() {
        return INSTANCE;
    }
}
