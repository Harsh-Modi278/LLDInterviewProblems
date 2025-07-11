package LRUCache.cache.storage;

import LRUCache.cache.exceptions.NotFoundException;
import LRUCache.cache.exceptions.StorageFullException;

public interface Storage<Key, Value> {
    void add(Key key, Value value) throws StorageFullException;
    Value get(Key key) throws NotFoundException;
    void remove(Key key);
}
