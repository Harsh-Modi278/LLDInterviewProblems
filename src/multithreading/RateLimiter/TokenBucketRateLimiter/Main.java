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
