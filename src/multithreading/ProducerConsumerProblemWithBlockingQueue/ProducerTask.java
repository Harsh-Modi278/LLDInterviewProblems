package multithreading.ProducerConsumerProblemWithBlockingQueue;

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
