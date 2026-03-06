import java.util.*;

public class Problem7 {

    private HashMap<String, Integer> queries = new HashMap<>();

    public void updateFrequency(String query) {
        queries.put(query, queries.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : queries.entrySet()) {
            if (entry.getKey().startsWith(prefix))
                pq.add(entry);
        }

        List<String> result = new ArrayList<>();

        int count = 0;

        while (!pq.isEmpty() && count < 10) {
            result.add(pq.poll().getKey());
            count++;
        }

        return result;
    }

    public static void main(String[] args) {

        Problem7 auto = new Problem7();

        auto.updateFrequency("java tutorial");
        auto.updateFrequency("javascript");
        auto.updateFrequency("java download");

        System.out.println(auto.search("jav"));
    }
}