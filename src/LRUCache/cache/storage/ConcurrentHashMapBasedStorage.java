package LRUCache.cache.storage;

import LRUCache.cache.exceptions.NotFoundException;
import LRUCache.cache.exceptions.StorageFullException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentHashMapBasedStorage<Key, Value> implements Storage<Key, Value> {
    private final ConcurrentHashMap<Key, Value> map;
    private final Integer capacity;
    private final ReentrantLock lock = new ReentrantLock();

    public ConcurrentHashMapBasedStorage(final Integer capacity) {
        this.capacity = capacity;
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        lock.lock();
        try {
            if (isStorageFull()) {
                throw new StorageFullException();
            }
            map.put(key, value);
        } finally {
            lock.unlock();
        }

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
