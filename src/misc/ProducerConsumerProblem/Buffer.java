package misc.ProducerConsumerProblem;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final int capacity;
    private final Queue<Integer> queue = new LinkedList<>();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    synchronized public void put(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(value);
        System.out.println("Put " + value + " to queue");
        printQueue();
        notifyAll();
    }

    synchronized public int get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int value = queue.poll();
        System.out.println("Get " + value + " from queue");
        printQueue();
        notifyAll();
        return value;
    }

    private void printQueue() {
        System.out.print("--- printQueue --- ");
        for (int value: queue) {
            System.out.print(value + " ");
        }
        System.out.println("\n");
    }
}
