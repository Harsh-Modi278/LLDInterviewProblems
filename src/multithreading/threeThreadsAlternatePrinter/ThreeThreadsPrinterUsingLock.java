package multithreading.threeThreadsAlternatePrinter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeThreadsPrinterUsingLock {
    private final int n;
    private int turn = 0; // 0->numbers, 1->uppercase, 2->lowercase

    private final Lock lock = new ReentrantLock();
    private final Condition numberCondition = lock.newCondition();
    private final Condition upperCaseCondition = lock.newCondition();
    private final Condition lowerCaseCondition = lock.newCondition();


    public ThreeThreadsPrinterUsingLock(int n) {
        this.n = n;
    }

    public void printNumbers() throws InterruptedException {
        for (int i=0;i<n;i++) {
            lock.lock();
            try {
                while (turn != 0) numberCondition.await();
                System.out.print(i+", ");
                // note: Always update the shared state before signaling,
                // so that when the waiting thread wakes up,
                // the condition it’s waiting for is already true.
                turn = 1;
                upperCaseCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printUppercase() throws InterruptedException {
        for (int i=0;i<n;i++) {
            lock.lock();
            try {
                while (turn != 1) upperCaseCondition.await();
                System.out.print((char)('A' + i)+", ");
                turn = 2;
                lowerCaseCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printLowercase() throws InterruptedException {
        for (int i=0;i<n;i++) {
            lock.lock();
            try {
                while (turn != 2) lowerCaseCondition.await();
                System.out.print((char)('a' + i)+", ");
                turn = 0;
                numberCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
