package misc.twoThreadsAlternatePrinter;

public class TwoThreadsAlternatePrinter {
    private final int n;
    private int turn = 0; // shared state - lock variable

    public TwoThreadsAlternatePrinter(int n) {
        this.n = n;
    }

    public void printNumbers() throws InterruptedException {
        for (int i=1;i<=n;i++) {
            synchronized (this) {
                while (turn !=0) wait();
                System.out.println(i+", ");
                turn = 1;
                notifyAll();
            }
        }
    }

    public void printCharacters() throws InterruptedException {
        for (int i=0;i<n;i++) {
            synchronized (this) {
                while (turn !=1) wait();
                System.out.println((char)('A' + i) + ",");
                turn = 0;
                notifyAll();
            }
        }
    }
}

// Notes
/*
- We use this in synchronized (this) in your problem because
all the threads need to coordinate their actions using the same lock object,
and this refers to the current instance of the class (TwoThreadsAlternatePrinter)
that contains the shared state (turn variable) and coordination logic

- All threads (e.g., the ones printing letters, numbers, etc.) share the same instance of the class
and thus the same monitor lock (this).

- When one thread enters the synchronized block, it acquires the lock on this,
preventing other threads from entering any other synchronized block on the same object
until the lock is released

- So the choice of lock object determines which threads synchronize with each other
*/
