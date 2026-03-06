import java.util.*;

public class Problem3 {

    static class DNSEntry {
        String ip;
        long expiry;

        DNSEntry(String ip, int ttl) {
            this.ip = ip;
            this.expiry = System.currentTimeMillis() + ttl * 1000;
        }
    }

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    private int hits = 0;
    private int misses = 0;

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && entry.expiry > System.currentTimeMillis()) {
            hits++;
            return "Cache HIT → " + entry.ip;
        }

        misses++;

        String newIP = queryUpstream(domain);

        cache.put(domain, new DNSEntry(newIP, 5));

        return "Cache MISS → " + newIP;
    }

    private String queryUpstream(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    public void getStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);

        System.out.println("Hit Rate: " + hitRate + "%");
    }

    public static void main(String[] args) {

        Problem3 dns = new Problem3();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));

        dns.getStats();
    }
}