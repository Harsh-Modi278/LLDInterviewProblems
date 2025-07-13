package multithreading.RateLimiter.TokenBucketRateLimiter;

import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter {
    private final int capacity;
    private final int refillRate; // tokens per second
    private long lastRefillTimestamp;
    private double currentTokens;
    private final ReentrantLock lock = new ReentrantLock();

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.lastRefillTimestamp = System.nanoTime();

        currentTokens = capacity;
        /**
         * At initialization, the bucket starts at its maximum capacity (full of tokens)
         * This allows the system to handle a burst of requests up to the bucket's capacity right away,
         * which is one of the main advantages of the token bucket approach
         */
    }

    private void refill() {
        long now = System.nanoTime();
        double nanoSecondsPassed = (now - lastRefillTimestamp); // e.g.,4,000,000,000 nano seconds
        double secondsPassed = nanoSecondsPassed/1e9;

        // 1 second -> refillRate tokens
        // (nanoSecondsPassed/1e9) seconds -> how many tokens

        double tokensToAdd = secondsPassed * refillRate;
        if (tokensToAdd > 0) {
            currentTokens = Math.min(currentTokens + tokensToAdd, capacity);
            lastRefillTimestamp = now;
        }
    }

    public boolean allowRequest() {
        lock.lock();
        try {
            refill();
            if (currentTokens >= 1) {
                currentTokens -= 1;
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }
}

/**
 * Q: Why refill is called inside allowRequest method?
 * Ans:
 * - Token Bucket works by accumulating tokens over time, up to a maximum (capacity).
 * - Each request should consume a token only if there are enough tokens at the current moment.
 * - Tokens are not refilled continuously in the background; instead, they are refilled "just in time"—whenever a request arrives,
 *   the bucket is updated based on elapsed time since the last refill.
 * - If you didn't call refill() inside allowRequest(), the token count would become stale,
 *   and the limiter would not enforce the correct rate.
 *
 */
