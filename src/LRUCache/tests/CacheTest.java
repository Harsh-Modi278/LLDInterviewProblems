package LRUCache.tests;

import LRUCache.cache.Cache;
import LRUCache.cache.factories.CacheFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CacheTest {
    Cache<Integer, Integer> cache;

    @BeforeEach
    public void setup() {
        cache = new CacheFactory<Integer, Integer>().defaultCache(3);
    }

    @Test
    public void testCache() {
        cache.put(1, 1); // DLL of keys: 1
        cache.put(2, 2); // DLL of keys: 1->2

        assertEquals(1, cache.get(1)); // DLL of keys: 2 -> 1

        cache.put(3, 3); // DLL of keys: 2 -> 1 -> 3
        assertEquals(3, cache.get(3)); // DLL of keys: 2 -> 1 -> 3

        // cache is full
        // try to add element, and eviction should happen of '2'
        cache.put(4, 4);

        cache.get(2); // should throw NotFoundException
    }
}
