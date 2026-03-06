import java.util.*;

public class Problem6 {

    static class TokenBucket {

        int tokens;
        int maxTokens;
        long lastRefill;

        TokenBucket(int max) {
            maxTokens = max;
            tokens = max;
            lastRefill = System.currentTimeMillis();
        }
    }

    private HashMap<String, TokenBucket> clients = new HashMap<>();
    private int LIMIT = 1000;

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT));

        TokenBucket bucket = clients.get(clientId);

        long now = System.currentTimeMillis();

        if (now - bucket.lastRefill > 3600000) {
            bucket.tokens = LIMIT;
            bucket.lastRefill = now;
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        Problem6 limiter = new Problem6();

        System.out.println(limiter.checkRateLimit("client1"));
        System.out.println(limiter.checkRateLimit("client1"));
    }
}
