package LRUCache.cache;

import LRUCache.cache.factories.CacheFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    public static void main(String[] args) {
        System.out.println("LRU Cache");

        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().defaultCache(3);

        cache.put(1, 1); // DLL of keys: 1
        cache.put(2, 2); // DLL of keys: 1->2

        System.out.println(cache.get(1)); // DLL of keys: 2 -> 1

        cache.put(3, 3); // DLL of keys: 2 -> 1 -> 3
        System.out.println(cache.get(3)); // DLL of keys: 2 -> 1 -> 3

        // cache is full
        // try to add element, and eviction should happen of '2'
        cache.put(4, 4);

        cache.get(2); // should throw NotFoundException
    }
}
