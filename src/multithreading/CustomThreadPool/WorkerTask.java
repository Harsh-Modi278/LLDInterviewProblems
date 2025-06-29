package multithreading.CustomThreadPool;

import java.util.concurrent.BlockingQueue;

public class WorkerTask implements Runnable{
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean running = true;
    // 1. In Java, each thread can cache variables locally for performance.
    // 2. If a variable is not declared volatile, one thread might update the value,
    //    but other threads may continue to see the old cached value, leading to inconsistent behavior.
    // 3. Declaring running as volatile guarantees that any write to running by one thread is immediately
    //    visible to all other threads. When one thread sets running = false,
    //    all worker threads will see this change and can exit their loop promptly.

    public WorkerTask(BlockingQueue<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Runnable task = taskQueue.take(); // take is blocking on empty queue
                task.run();
            } catch (InterruptedException e) {
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
