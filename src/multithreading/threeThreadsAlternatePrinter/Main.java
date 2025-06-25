package multithreading.threeThreadsAlternatePrinter;

public class Main {
    public static void main(String[] args) {
//        ThreeThreadsAlternatePrinter printer = new ThreeThreadsAlternatePrinter(10);
        ThreeThreadsPrinterUsingLock printer = new ThreeThreadsPrinterUsingLock(10);

        Thread t1 = new Thread(() -> {
            try {
                printer.printNumbers();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                printer.printUppercase();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                printer.printLowercase();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t2.start();
        t1.start();
        t3.start();
    }
}
