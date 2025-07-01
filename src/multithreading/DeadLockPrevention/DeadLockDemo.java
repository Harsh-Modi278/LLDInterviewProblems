package multithreading.DeadLockPrevention;

public class DeadLockDemo {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void methodA() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock1...");
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            synchronized (lock2) {
                System.out.println("Thread 1: Acquired lock2!");
            }
        }
    }

    public void methodB() {
        synchronized (lock2) {
            System.out.println("Thread 2: Holding lock2...");
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            synchronized (lock1) {
                System.out.println("Thread 2: Acquired lock1!");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        Thread t1 = new Thread(demo::methodA);
        Thread t2 = new Thread(demo::methodB);
        t1.start();
        t2.start();
    }
}

/**
 * What happens:
 *
 *     Thread 1 locks lock1 and waits for lock2.
 *
 *     Thread 2 locks lock2 and waits for lock1.
 *
 *     Both threads are blocked forever—a deadlock
 */

/**
 * demo::safeMethodA is a method reference—a shorthand for saying
 * “call the safeMethodA method on the demo object.”
 *
 * The Thread constructor expects a Runnable: public Thread(Runnable target)
 * Runnable is a functional interface with a single method:
 * public interface Runnable {
 *     void run();
 * }
 *
 * How does demo::safeMethodA fit as a Runnable?
 * 1. demo::safeMethodA is a reference to a method with the signature void safeMethodA().
 * 2. This matches the void run() method of Runnable.
 * 3. Java automatically “wraps” demo::safeMethodA as a Runnable—when run() is called, it will invoke demo.safeMethodA().
 *
 * Equivalent lambda: Thread t1 = new Thread(() -> demo.safeMethodA());
 *
 * Equivalent anonymous class:
 *
 * Thread t1 = new Thread(new Runnable() {
 *     @Override
 *     public void run() {
 *         demo.safeMethodA();
 *     }
 * });
 *
 */
