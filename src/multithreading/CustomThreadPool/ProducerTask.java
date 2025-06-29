package multithreading.CustomThreadPool;

public class ProducerTask implements Runnable{
    private final int taskNum;

    ProducerTask(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " executing task" + taskNum);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
            // no-op
        } ;
    }
}
