package LRUCache.cache;

import LRUCache.cache.factories.CacheFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("LRU Cache");

//        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().defaultCache(3);
//
//        cache.put(1, 1); // DLL of keys: 1
//        cache.put(2, 2); // DLL of keys: 1->2
//
//        System.out.println(cache.get(1)); // DLL of keys: 2 -> 1
//
//        cache.put(3, 3); // DLL of keys: 2 -> 1 -> 3
//        System.out.println(cache.get(3)); // DLL of keys: 2 -> 1 -> 3
//
//        // cache is full
//        // try to add element, and eviction should happen of '2'
//        cache.put(4, 4);
//
//        cache.get(2); // should throw NotFoundException

        ThreadSafeCache<Integer, Integer> threadSafeCache = new CacheFactory<Integer, Integer>().threadSafeCache(3);
        System.out.println("\nMulti-threaded LRU Cache Test:");
        Runnable writer = () -> {
            for (int i = 5; i < 10; i++) {
                threadSafeCache.put(i, i);
                System.out.println("Put " + i + ": " + i);
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            }
        };

        Runnable reader = () -> {
            for (int i = 5; i < 10; i++) {
                Integer value = threadSafeCache.get(i);
                System.out.println("Get " + i + ": " + value);
                try { Thread.sleep(15); } catch (InterruptedException ignored) {}
            }
        };

        Thread t1 = new Thread(writer);
        Thread t2 = new Thread(reader);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Final state
        System.out.println("\nFinal cache state:");
        for (int i = 0; i < 12; i++) {
            System.out.println("Key " + i + ": " + threadSafeCache.get(i));
        }
    }
}
