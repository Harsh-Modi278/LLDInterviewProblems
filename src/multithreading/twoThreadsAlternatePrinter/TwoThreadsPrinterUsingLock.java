package multithreading.twoThreadsAlternatePrinter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TwoThreadsPrinterUsingLock {
    private final int n;
    private int turn = 0; // shared state, 0: numbers, 1: characters

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public TwoThreadsPrinterUsingLock(int n) {
        this.n = n;
    }

    public void printNumbers() throws InterruptedException {
        for (int i=1;i<=n;i++) {
            lock.lock();
            try {
                while (turn != 0) condition.await();
                System.out.print(i+", ");
                turn = 1;
                condition.signal();
            }
            finally {
                lock.unlock();
            }
        }
    }

    public void printCharacters() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (turn != 1) {
                    condition.await();
                }
                System.out.print((char)('A' + i) + ", ");
                turn = 0;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
