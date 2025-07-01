package multithreading.DeadLockPrevention;

public class DeadlockPreventionUsingLockOrdering {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void safeMethodA() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock1...");
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            synchronized (lock2) {
                System.out.println("Thread 1: Acquired lock2!");
            }
        }
    }

    public void safeMethodB() {
        synchronized (lock1) { // Changed order: lock1 first, then lock2
            System.out.println("Thread 2: Holding lock1...");
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            synchronized (lock2) {
                System.out.println("Thread 2: Acquired lock2!");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockPreventionUsingLockOrdering demo = new DeadlockPreventionUsingLockOrdering();
        Thread t1 = new Thread(demo::safeMethodA);
        Thread t2 = new Thread(demo::safeMethodB);
        t1.start();
        t2.start();
    }
}

/**
 * What’s different:
 *
 *     Both methods acquire lock1 before lock2.
 *
 *     No circular waiting is possible, so no deadlock can occur
 *
 *  Note: Deadlock prevention via lock ordering.
 *  Always acquire multiple locks in the same, global order in every thread
 */
