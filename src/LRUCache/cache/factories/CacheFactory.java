package LRUCache.cache.factories;

import LRUCache.cache.Cache;
import LRUCache.cache.policies.LRUEvictionPolicy;
import LRUCache.cache.storage.HashMapBasedStorage;

import java.util.HashMap;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> defaultCache(final Integer capacity) {
        LRUEvictionPolicy<Key> evictionPolicy = new LRUEvictionPolicy<>();
        HashMapBasedStorage<Key, Value> map = new HashMapBasedStorage<>(capacity);
        return new Cache<Key, Value>(evictionPolicy, map);
    }
}
