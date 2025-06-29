package multithreading.CustomThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Thread> workerThreads;

    public CustomThreadPool(int numThreads) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workerThreads = new ArrayList<> ();

        for (int i = 0;i < numThreads; i++) {
            WorkerTask workerTask = new WorkerTask(taskQueue);
            Thread thread = new Thread(workerTask, "Worker-" + (i+1));
            thread.start();
            workerThreads.add(thread);
        }
    }

    public void submit(Runnable task) {
        taskQueue.offer(task); // offer is not blocking
    }

    public void shutdown() {
        // Interrupt all worker threads
        for (Thread thread : workerThreads) {
            thread.interrupt();
        }
    }
}
