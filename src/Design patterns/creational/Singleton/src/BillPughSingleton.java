// Three common patterns of singleton implementation
// 1) Private constructor to restrict instantiation of the class from other classes.
// 2) Private static variable of the same class that is the only instance of the class.
// 3) Public static method that returns the instance of the class,
// this is the global access point for the outer world to get the instance of the singleton class.


public class BillPughSingleton {
    // private constructor
    private BillPughSingleton(){};

    // static inner class - inner classes are not loaded until they are referenced.
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    // global access point
    public static BillPughSingleton getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread("t1");
        Thread t2 = new MyThread("t2");

        t1.start();
        t2.start();

        System.out.println("Hello world!");
    }
}

// The "loading" is the process of initialising the inner class (SingletonHelper).
// "until they are referenced" means the inner class (SingletonHelper) is not initialised
// until the SingletonHelper.INSTANCE static field is referenced ("used somewhere") in this case.
// So the call to getInstance() references SingletonHelper.INSTANCE and
// starts the initialisation of the SingletonHelper inner class. ("lazily")
// Important: The whole process of initialising a class is synchronised by the JVM.