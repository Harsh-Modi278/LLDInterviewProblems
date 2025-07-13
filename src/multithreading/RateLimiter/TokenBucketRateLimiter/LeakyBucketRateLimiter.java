package multithreading.RateLimiter.TokenBucketRateLimiter;

import java.util.concurrent.locks.ReentrantLock;

public class LeakyBucketRateLimiter {
    private final int capacity;
    private final int leakRate; // requests per second

    private int currentRequestsInBucket; // current number of requests in the bucket
    private long lastLeakTimestamp;

    private final ReentrantLock lock = new ReentrantLock();

    public LeakyBucketRateLimiter(int capacity, int leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;

        this.currentRequestsInBucket = 0;
        this.lastLeakTimestamp = System.nanoTime();
    }

    private void leakRequests() {
        long now = System.nanoTime();
        double nanoSecondsPassed = (now - lastLeakTimestamp); // e.g.,4,000,000,000 nano seconds
        double secondsPassed = nanoSecondsPassed/1e9;

        int leakedRequests = (int)(secondsPassed * leakRate);
        if (leakedRequests > 0) {
            currentRequestsInBucket = Math.max(0, currentRequestsInBucket - leakedRequests);
            lastLeakTimestamp = now;
        }
    }

    public boolean allowRequest() {
        lock.lock();
        try {
            leakRequests();
            if (currentRequestsInBucket < capacity) {
                currentRequestsInBucket += 1;
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }
}

/**
 * - Requests are added to a bucket (queue).
 * - Requests leak out (are processed) at a constant rate.
 * - If the bucket (queue) is full, new requests are dropped or delayed.
 * - Enforces a steady, predictable request rate.
 */
