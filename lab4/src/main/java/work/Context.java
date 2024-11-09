package work;

import files.FilesUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Context {
    private final Queue<String> queue = new LinkedList<>();
    private final Queue<String> priority = new LinkedList<>();
    private final List<Semaphore> semaphores;
    private final Semaphore readers;
    private final Set<String> all = new HashSet<>();
    private final Set<String> used = new HashSet<>();
    private final FilesUtil filesUtil;
    private final int maxCount;

    public Context(@NotNull String dir, int maxCount, int threads) throws IOException {
        this.maxCount = maxCount;
        queue.add("/");
        filesUtil = new FilesUtil(dir);
        readers = new Semaphore(threads > 0 ? threads : 1, true);
        semaphores = new ArrayList<>();
        for (var i = 0; i < threads; ++i) {
            semaphores.add(new Semaphore(1, true));
        }
        if (threads == 0) {
            semaphores.add(new Semaphore(1, true));
        }
    }

    public Queue<String> getQueue() {
        return queue;
    }

    public FilesUtil getFilesUtil() {
        return filesUtil;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public Set<String> getAll() {
        return all;
    }

    public Set<String> getUsed() {
        return used;
    }

    public Queue<String> getPriority() {
        return priority;
    }

    public List<Semaphore> getSemaphores() {
        return semaphores;
    }

    public Semaphore getReaders() {
        return readers;
    }
}
