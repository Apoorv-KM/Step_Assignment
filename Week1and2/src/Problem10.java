import java.util.*;

public class Problem10 {

    private LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10000, 0.75f, true);

    private HashMap<String, String> L2 = new HashMap<>();

    private HashMap<String, String> database = new HashMap<>();

    public String getVideo(String videoId) {

        if (L1.containsKey(videoId)) {
            System.out.println("L1 Cache HIT");
            return L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {

            System.out.println("L2 Cache HIT");

            String data = L2.get(videoId);

            L1.put(videoId, data);

            return data;
        }

        if (database.containsKey(videoId)) {

            System.out.println("Database HIT");

            String data = database.get(videoId);

            L2.put(videoId, data);

            return data;
        }

        return "Video not found";
    }

    public void addVideo(String videoId, String data) {
        database.put(videoId, data);
    }

    public static void main(String[] args) {

        Problem10 cache = new Problem10();

        cache.addVideo("video123", "Movie Data");

        System.out.println(cache.getVideo("video123"));
        System.out.println(cache.getVideo("video123"));
    }
}