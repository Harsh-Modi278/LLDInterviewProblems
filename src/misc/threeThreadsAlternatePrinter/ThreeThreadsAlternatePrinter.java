package misc.threeThreadsAlternatePrinter;

public class ThreeThreadsAlternatePrinter {
    private final int n;
    private int turn = 0;


    public ThreeThreadsAlternatePrinter(int n) {
        this.n = n;
    }

    void printNumbers() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                while (turn !=0) wait();
                System.out.println(i + ", ");
                turn = 1;
                notifyAll();
            }
        }
    }

    void printUppercase() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (turn !=1) wait();
                System.out.println((char)('A' + i) + ", ");
                turn = 2;
                notifyAll();
            }
        }
    }

    void printLowercase() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (turn !=2) wait();
                System.out.println((char)('a' + i) + ", ");
                turn = 0;
                notifyAll();
            }
        }
    }
}
