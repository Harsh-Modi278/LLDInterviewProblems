package misc.twoThreadsAlternatePrinter;

public class Main {
    public static void main(String[] args) {
        TwoThreadsAlternatePrinter printer = new TwoThreadsAlternatePrinter(10);

        Thread t1 = new Thread(() -> {
            try {
                printer.printCharacters();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                printer.printNumbers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }
}
