package LRUCache.cache;

import LRUCache.cache.exceptions.NotFoundException;
import LRUCache.cache.exceptions.StorageFullException;
import LRUCache.cache.policies.ConcurrentLRUEvictionPolicy;
import LRUCache.cache.policies.EvictionPolicy;
import LRUCache.cache.storage.ConcurrentHashMapBasedStorage;
import LRUCache.cache.storage.Storage;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCache<Key, Value> {
    private final ConcurrentLRUEvictionPolicy<Key> evictionPolicy;
    private final ConcurrentHashMapBasedStorage<Key, Value> storage;
    private final ReentrantLock lock = new ReentrantLock();


    public ThreadSafeCache(ConcurrentLRUEvictionPolicy<Key> evictionPolicy, ConcurrentHashMapBasedStorage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public Value get(Key key) {
        lock.lock();
        try {
            Value value = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException e) {
            System.out.println(String.format("key %s does not exist in the cache", key));
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void put(Key key, Value value) {
        lock.lock();
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
        } finally {
            lock.unlock();
        }
    }
}

/**
 * While each component like ConcurrentHashMapBasedStorage and ConcurrentLRUEvictionPolicy
 * is thread-safe for single operations, your cache methods (get, put) perform compound actions across multiple data structures:
 * In put:
 * - Check if storage is full.
 * - Possibly evict a key from both the eviction policy and storage.
 * - Add the new key to both eviction policy and storage.
 *
 * In get:
 * - Retrieve the value from storage.
 * - Update the access order in the eviction policy.
 * - These steps are not atomic as a whole. If multiple threads call put or get concurrently,
 *  the state of the cache, eviction policy, and storage can become inconsistent.
 *  For example, two threads could both see that the cache is not full, both add new keys,
 *  and temporarily exceed the intended capacity or corrupt the LRU order.
 *
 *  Interview explanation:
 *  "In my implementation, I used ConcurrentHashMap for thread-safe storage and
 *  ConcurrentLinkedDeque to track access order for LRU eviction.
 *
 *  While both collections are thread-safe for individual operations,
 *  I recognized that compound actions—like updating the cache and the access order together—
 *  require extra coordination.
 *
 *  To ensure correctness, I used a ReentrantLock to synchronize these compound operations,
 *  so the cache size and order remain consistent even under high concurrency.
 *  This design balances performance (by allowing concurrent reads/writes where possible)
 *  with correctness (by locking only when necessary), and keeps complexity manageable by
 *  using well-tested Java concurrency primitives.
 *
 *  Overall, my approach demonstrates an understanding of the real-world challenges
 *  of concurrent data structure design, the tradeoffs involved,
 *  and the importance of coordinating operations across multiple thread-safe collections."
 */
