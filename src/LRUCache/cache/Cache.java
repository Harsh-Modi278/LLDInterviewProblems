package LRUCache.cache;

import LRUCache.cache.exceptions.NotFoundException;
import LRUCache.cache.exceptions.StorageFullException;
import LRUCache.cache.policies.EvictionPolicy;
import LRUCache.cache.storage.Storage;

public class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public Value get(Key key) {
        try {
            Value value = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException e) {
            System.out.println(String.format("key %s does not exist in the cache", key));
            return null;
        }
    }

    public void put(Key key, Value value) {
        try {
            storage.add(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException e) {
            // storage is full, find key to evict
            Key keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Storage is full and found no key to evict");
            }
            storage.remove(keyToRemove);
            put(key, value);
        }
    }
}
