package LRUCache.cache.factories;

import LRUCache.cache.Cache;
import LRUCache.cache.ThreadSafeCache;
import LRUCache.cache.policies.ConcurrentLRUEvictionPolicy;
import LRUCache.cache.policies.LRUEvictionPolicy;
import LRUCache.cache.storage.ConcurrentHashMapBasedStorage;
import LRUCache.cache.storage.HashMapBasedStorage;

import java.util.HashMap;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> defaultCache(final Integer capacity) {
        LRUEvictionPolicy<Key> evictionPolicy = new LRUEvictionPolicy<>();
        HashMapBasedStorage<Key, Value> map = new HashMapBasedStorage<>(capacity);
        return new Cache<Key, Value>(evictionPolicy, map);
    }

    public ThreadSafeCache<Key, Value> threadSafeCache(final Integer capacity) {
        ConcurrentLRUEvictionPolicy<Key> evictionPolicy = new ConcurrentLRUEvictionPolicy<>();
        ConcurrentHashMapBasedStorage<Key, Value> map = new ConcurrentHashMapBasedStorage<>(capacity);
        return new ThreadSafeCache<>(evictionPolicy, map);
    }
}
