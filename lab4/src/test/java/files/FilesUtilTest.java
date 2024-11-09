package files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilesUtilTest {
    private static final String name = "test-output";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walkFileTree(Paths.get(name), DeleteFileVisitor.getInstance());
    }

    @Test
    void assemblePath() {
        try {
            var parts = new String[]{"a", "b", "c", "d"};
            var util = new FilesUtil(name);
            var result = util.assemblePath(parts);
            assertNotNull(result);
            assertFalse(Files.exists(result));
            assertTrue(Files.exists(result.getParent()));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void assembleEmptyPath() {
        try {
            var util = new FilesUtil(name);
            assertEquals(name, util.assemblePath().toString());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void save() {
        try {
            var data = "Hello, world!";
            var filename = "file.txt";
            var util = new FilesUtil(name);
            var path = util.assemblePath(filename);
            util.save(() -> Stream.of(data), path);
            try (var in = Files.newBufferedReader(path)) {
                in.lines().forEach(line -> assertEquals(data, line));
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void remove() {
        try {
            var parts = new String[]{"a", "b", "c", "d.txt"};
            var util = new FilesUtil(name);
            var path = util.assemblePath(parts);
            util.save(() -> Stream.of(""), path);
            util.remove(path);
            assertFalse(Files.exists(path));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void removeEmpty() {
        save();
        remove();
        try {
            var data = "Hello, world!";
            var filename = "file.txt";
            var util = new FilesUtil(name);
            var path1 = util.assemblePath(filename);
            util.save(() -> Stream.of(data), path1);
            var parts = new String[]{"a", "b", "c", "d.txt"};
            var path2 = util.assemblePath(parts);
            util.save(() -> Stream.of(""), path2);
            util.remove(path2);
            util.removeEmpty();
            assertTrue(Files.exists(path1));
            assertFalse(Files.exists(path2));
            assertFalse(Files.exists(Paths.get(name, parts[0])));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}