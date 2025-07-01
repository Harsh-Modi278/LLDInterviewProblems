package multithreading.DeadLockPrevention;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockPreventionUsingReentrantLock {
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    public void methodA() {
        while (true) { // long polling
            if (lock1.tryLock()) { // non-blocking, true if gets the lock, else false
                try {
                    System.out.println("Thread-A: Acquired lock1");
                    if (lock2.tryLock(500, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("Thread-A: Acquired lock2");
                            // critical section
                            break;
                        } finally {
                            lock2.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Backoff before retrying in long polling
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        }
    }

    public void methodB() {
        while (true) {
            if (lock2.tryLock()) {
                try {
                    System.out.println("Thread-B: Acquired Lock2");
                    if (lock1.tryLock(500, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println("Thread-B: Acquired Lock1");
                            // Critical section
                            break;
                        } finally {
                            lock1.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock2.unlock();
                }
            }
            // Backoff before retrying in long polling
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) {
        DeadLockPreventionUsingReentrantLock demo = new DeadLockPreventionUsingReentrantLock();
        Thread t1 = new Thread(demo::methodA);
        Thread t2 = new Thread(demo::methodB);

        t1.start();
        t2.start();
    }
}

/**
 * No deadlock: The use of tryLock with a timeout and backoff ensures that threads do not wait forever.
 *
 * When to Use This Pattern
 *     1. When you need to acquire multiple locks in different orders (legacy code, third-party libraries, etc.).
 *     2. [IMP] When you can't globally order lock acquisition.
 *     3. When deadlock is a risk and you want a simple, robust prevention mechanism.
 */
