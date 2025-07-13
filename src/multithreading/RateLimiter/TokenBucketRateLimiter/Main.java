package multithreading.RateLimiter.TokenBucketRateLimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(3, 2); // 3 tokens max, refillRate = 2 tokens /sec
        LeakyBucketRateLimiter leakyBucketLimiter = new LeakyBucketRateLimiter(3, 1);

        Runnable task = () -> {
            for (int i=1;i<=4;i++) {
                boolean allowed = leakyBucketLimiter.allowRequest();
                System.out.println(Thread.currentThread().getName() + " request " + i + ": " + (allowed ? "ALLOWED" : "BLOCKED"));
                try {
                    Thread.sleep(200); // time in seconds between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
//        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
//        t2.start();
    }
}

/**
 * - Token Bucket
 *   - is preferred when flexibility and burst handling are needed.
 *   - No packet/request loss (if tokens are available): Requests wait until tokens are available rather than being dropped immediately.
 *
 *   * How Token Bucket Handles Requests
 *     - Tokens accumulate in the bucket at a fixed rate, up to a maximum capacity
 *     - When a request arrives:
 *       1. If there are enough tokens, the request is allowed and tokens are removed
 *       2. If there are not enough tokens, the request is rejected or must wait until tokens are refilled,
 *          depending on the system's implementation
 *
 * - Leaky Bucket
 *  - is ideal when a steady, predictable flow is required and bursts must be strictly controlled.
 *  - Potential data/request loss: If incoming rate exceeds the leak rate, excess requests are discarded.
 *
 */
