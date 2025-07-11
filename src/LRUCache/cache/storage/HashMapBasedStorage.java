package LRUCache.cache.storage;

import LRUCache.cache.exceptions.NotFoundException;
import LRUCache.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value> {
    private Map<Key, Value> map;
    private final int capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) {
            throw new StorageFullException();
        }
        map.put(key, value);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        throw new NotFoundException();
    }

    @Override
    public void remove(Key key) {
        map.remove(key);
    }

    private boolean isStorageFull() {
        return map.size() == capacity;
    }
}
