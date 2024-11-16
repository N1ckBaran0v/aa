package threads;

import database.DatabaseService;
import files.LogsMerger;
import tasks.Task;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public final class ThreadsScheduler {
    private ThreadsScheduler() {
    }

    public static void execute() throws Exception {
        var outputDirectory = Paths.get("output");
        if (!Files.exists(outputDirectory)) {
            Files.createDirectory(outputDirectory);
        }
        var queue1 = new LinkedList<Task>();
        var queue2 = new LinkedList<Task>();
        var queue3 = new LinkedList<Task>();
        var queue4 = new LinkedList<Task>();
        var semaphore = new Semaphore(1);
        var databaseService = new DatabaseService(Paths.get("output", "recipes.sqlite").toString());
        var creatorPath = Paths.get("output", "creator.log");
        var creator = new Thread(new TasksCreator(queue1, creatorPath));
        var readerPath = Paths.get("output", "reader.log");
        var reader = new Thread(new FileReader(queue1, queue2, readerPath, "read"));
        var parserPath = Paths.get("output", "parser.log");
        var parser = new Thread(new DataParser(queue2, queue3, parserPath, "parse"));
        var saverPath = Paths.get("output", "saver.log");
        var saver = new Thread(new DataSaver(queue3, queue4, saverPath, "save", databaseService));
        var destroyerPath = Paths.get("output", "destroyer.log");
        var destroyer = new Thread(new TasksDestroyer(queue4, destroyerPath, semaphore));
        creator.start();
        reader.start();
        parser.start();
        saver.start();
        destroyer.start();
        semaphore.acquire();
        System.out.println("Обработка рецептов завершена");
        LogsMerger.mergeLogs(creatorPath, readerPath, parserPath, saverPath, destroyerPath);
    }
}
