import java.util.*;

public class Problem5 {

    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> sourceCount = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        sourceCount.put(source, sourceCount.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {

        System.out.println("Top Pages:");

        for (String url : pageViews.keySet()) {

            int views = pageViews.get(url);
            int unique = uniqueVisitors.get(url).size();

            System.out.println(url + " - " + views +
                    " views (" + unique + " unique)");
        }

        System.out.println("\nTraffic Sources:");

        for (String source : sourceCount.keySet()) {
            System.out.println(source + " : " + sourceCount.get(source));
        }
    }

    public static void main(String[] args) {

        Problem5 dashboard = new Problem5();

        dashboard.processEvent("/news", "user1", "google");
        dashboard.processEvent("/news", "user2", "facebook");
        dashboard.processEvent("/sports", "user3", "direct");

        dashboard.getDashboard();
    }
}