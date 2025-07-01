package multithreading.DeadLockPrevention;

import java.util.Arrays;
import java.util.Comparator;

public class MultiLockOrdering {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private final Object lock3 = new Object();

    public void safeAccessFixed() {
        synchronized (lock1) {
            synchronized (lock2) {
                synchronized (lock3) {
                    // Critical section
                    System.out.println("Inside fixed order critical section.");
                }
            }
        }
    }

    public void safeAccess() {
        runWithLocks(new Object[]{lock1, lock2, lock3}, () -> {
            // Critical section
            System.out.println("Inside fixed order critical section.");
        });
    }

    // The term "resources" here simply refers to the objects you want to lock on.
    // In Java, any object can be used as a lock, so an array of "resources"
    // is just an array of objects to be synchronized on.
    public void flexibleAccess(Object resourceA, Object resourceB, Object resourceC) {
        runWithLocks(new Object[]{resourceA, resourceB, resourceC}, () -> {
            // Critical section
            System.out.println("Inside flexible critical section with all locks held.");
        });
    }

    /**
     * Utility method to acquire locks in a consistent order and run the critical section.
     * @param locks Array of lock objects
     * @param criticalSection Code to run with all locks held
     */
    private void runWithLocks(Object[] locks, Runnable criticalSection) {
        // Sort locks by identity hash code to ensure consistent order
        Object[] sortedLocks = locks.clone();
        Arrays.sort(sortedLocks, Comparator.comparingInt(System::identityHashCode));
        runWithLocksRecursive(sortedLocks, 0, criticalSection);
    }

    /**
     * Recursive helper to acquire locks one by one and finally run the critical section.
     */
    private void runWithLocksRecursive(Object[] locks, int index, Runnable criticalSection) {
        if (index == locks.length) {
            criticalSection.run();
            return;
        }
        synchronized (locks[index]) {
            runWithLocksRecursive(locks, index + 1, criticalSection);
        }
    }

    public static void main(String[] args) {
        MultiLockOrdering mlo = new MultiLockOrdering();
        Object resourceA = new Object();
        Object resourceB = new Object();
        Object resourceC = new Object();

        // Fixed order locking
        mlo.safeAccessFixed();

        // Flexible order locking (order of arguments does not matter)
        mlo.flexibleAccess(resourceA, resourceB, resourceC);
        mlo.flexibleAccess(resourceC, resourceA, resourceB); // Safe!
    }
}

