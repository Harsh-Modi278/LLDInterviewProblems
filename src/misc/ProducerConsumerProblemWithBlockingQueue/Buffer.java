package misc.ProducerConsumerProblemWithBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Buffer {
    private final BlockingQueue<Integer> blockingQueue;

    public Buffer(int capacity) {
        this.blockingQueue = new ArrayBlockingQueue<>(capacity);
    }

    public void put(int value) throws InterruptedException {
        blockingQueue.put(value); // Automatically blocks when full
        System.out.println("Put " + value + " to queue");
//        printQueueSnapshot();
    }

    public int get() throws InterruptedException {
        int value = blockingQueue.take(); // Automatically blocks when empty
        System.out.println("Get " + value + " from queue");
//        printQueueSnapshot();
        return value;
    }

    /*
    * 1. Even without the synchronized block the printQueue method is thread-safe
    *    as all methods that modify the blockingQueue (buffer.put and buffer.get) are thread-safe
    *
    * 2. Iterating over a BlockingQueue is also safe in the sense that it will not throw a ConcurrentModificationException.
    *
    * 3. However, the iterator is weakly consistent:
        - It reflects some (but not necessarily all) of the elements present in the queue at the time of iteration.
        - It does not guarantee a "snapshot" view; elements may be added or removed by other threads during iteration.
        - So the output won't always be accurate without the synchronized block.
        - For example, you might see a queue state that is already changed by another thread by the time printQueue() runs.
    * */
    private void printQueueSnapshot() {
        synchronized (blockingQueue) {
            System.out.print("--- printQueue --- ");
            for (int value: blockingQueue) {
                System.out.print(value + " ");
            }
            System.out.println("\n");
        }
    }
}

/**
 * Notes:
 * 1. BlockingQueue has built-in thread-safety so no need of explicit synchronized blocks
 *    and handles all locking internally
 *
 * 2. put and take are blocking. put(E e) blocks when full and take() blocks when empty so no need
 *    of wait and notifyAll()
 *
 * 3. offer(E e) and poll() are non-blocking core methods on the BlockingQueue
 *    - The offer(E e) method tries to insert the specified element into the queue immediately
 *    - If the queue is full, it does not wait for space to become available, and returns false right away.
 *    - Useful when you want to try adding an element but don’t want your thread to wait if the queue is full.
 *
 *    - The poll() method tries to remove and return the head (front) of the queue immediately.
 *    - If the queue is empty, it does not wait for an element to become available.
 *      Instead, it simply returns null right away.
 *    - Useful when you want to try removing an element but don’t want your thread to wait if the queue is empty.
 *
 * 4. This implementation handles backpressure:
 *    - When producers try to add items faster than consumers can remove them,
 *      the queue — if bounded will eventually become full.
 *      At this point, the BlockingQueue automatically blocks the producer threads trying to
 *      add more items until space becomes available (i.e., consumers remove items).
 *      This blocking behavior applies backpressure to the producers,
 *      preventing them from overwhelming the system or exhausting memory by endlessly adding items.
 *   -  This is also true for the wait/notifyAll implementation.
 *   -  Without backpressure, producers can generate data faster than consumers can process,
 *      causing unbounded memory growth or system crashes.
 *   -  Backpressure ensures the system self-regulates: producers slow down naturally when the queue is full.
 *
 * 5. Happens-Before" Guarantee provided by the BlockingQueue
 *   - When one thread puts an element in the queue and another thread takes it,
 *     the changes made by the producer are visible to the consumer.
 *     This is due to proper memory synchronization
 *
 * 6. BlockingQueue uses Two-Condition Algorithm
 *    - Internally, the queue uses two conditions:
 *      1. notFull (for producers waiting to add)
        2. notEmpty (for consumers waiting to remove)
 *     This ensures efficient signaling and avoids unnecessary wakeups
 *
 * 7. What to say during interview:
 *   - "BlockingQueue is a thread-safe queue that supports blocking operations
 *      for both producers and consumers.
 *      Internally, it uses locks and condition variables to ensure that producers block
 *      when the queue is full and consumers block when the queue is empty.
 *      All operations are atomic and provide proper memory visibility between threads,
 *      so you don't have to use explicit synchronization or worry about data races."
 */
