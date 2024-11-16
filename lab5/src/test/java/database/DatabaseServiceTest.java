package database;

import files.DeleteFileVisitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseServiceTest {
    private static Path directory = Path.of("test-output");
    private DatabaseService databaseService;

    @BeforeEach
    void setUp() throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }
        databaseService = new DatabaseService(Path.of(directory.toString(), "database.sqlite").toString());
    }

    @Test
    void checkInsertion() throws IOException {
        var recipe = new Recipe(1, "no-url-here", "test", new ArrayList<>(), new ArrayList<>(), "another-no-url-here");
        databaseService.addRecipe(recipe);
        var got = databaseService.getRecipe(1);
        assertEquals(recipe, got);
    }

    @AfterEach
    void tearDown() throws IOException {
        databaseService = null;
        Files.walkFileTree(directory, DeleteFileVisitor.getInstance());
    }
}