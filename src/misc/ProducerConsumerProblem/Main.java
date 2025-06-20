package misc.ProducerConsumerProblem;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Thread producerThread = new Thread(new ProducerTask(buffer));
        Thread consumerThread = new Thread(new ConsumerTask(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
