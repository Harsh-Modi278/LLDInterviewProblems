package multithreading.ProducerConsumerProblemWithBlockingQueue;

public class ConsumerTask implements Runnable{
    private final Buffer buffer;

    public ConsumerTask(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = buffer.get();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}