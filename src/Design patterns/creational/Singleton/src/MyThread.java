public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("MyThread start: " + this.getName());
        try{
            Thread.sleep(1000);
            // some processing
            BillPughSingleton obj = BillPughSingleton.getInstance();
            System.out.println("Singleton obj: " + obj.hashCode());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyThread end: " + this.getName());
    }
}
