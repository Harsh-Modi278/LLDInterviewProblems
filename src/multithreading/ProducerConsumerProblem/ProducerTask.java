package multithreading.ProducerConsumerProblem;

public class ProducerTask implements Runnable {
    private final Buffer buffer;

    public ProducerTask(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            while (true) {
                buffer.put(value);
                value++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// About Thread.currentThread.interrupt();
// 1. When a thread is blocked in a method like wait(), sleep(), or join(),
// and another thread calls interrupt() on it, an InterruptedException is thrown.

// 2. When you catch this InterruptedException, the thread’s interrupted status is cleared
// (set back to false) by the JVM

// 3. If you simply catch and swallow the exception, higher-level code will not know
// the thread was interrupted, potentially causing problems for code that relies on the interrupted status

// 4. To preserve the interruption request, you should call Thread.currentThread().interrupt(); inside the catch block.
// This sets the interrupted flag back to true, allowing other code or layers to detect and
// respond to the interruption appropriately
