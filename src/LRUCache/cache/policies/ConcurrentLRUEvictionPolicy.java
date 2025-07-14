package LRUCache.cache.policies;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLRUEvictionPolicy<Key> implements EvictionPolicy<Key> {
    private final ConcurrentLinkedDeque<Key> dll;
    private final ReentrantLock lock = new ReentrantLock();

    public ConcurrentLRUEvictionPolicy() {
        this.dll = new ConcurrentLinkedDeque<Key>();
    }

    @Override
    public void keyAccessed(Key key) {
        lock.lock();
        try {
            dll.remove(key);
            dll.addLast(key);
        } finally {
            lock.unlock();
        }

    }

    @Override
    public Key evictKey() {
        lock.lock();
        try {
            Key firstNode = dll.pollFirst();
            if (firstNode == null) {
                return null;
            }

            dll.remove(firstNode);
            return firstNode;
        } finally {
            lock.unlock();
        }

    }
}
