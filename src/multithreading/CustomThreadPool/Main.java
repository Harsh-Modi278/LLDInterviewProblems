package multithreading.CustomThreadPool;

public class Main {
    public static void main(String[] args) {
        // a custom thread pool with 3 worker threads
        CustomThreadPool pool = new CustomThreadPool(3);

        // submit 10 tasks
        for (int i=1;i<=10;i++) {
            pool.submit(new ProducerTask(i));
        }

        pool.shutdown();
    }
}
