import java.util.*;

public class Problem4 {

    private HashMap<String, Set<String>> ngramMap = new HashMap<>();
    private int N = 5;

    public void addDocument(String docId, String text) {
        String[] words = text.split("\\s+");

        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder gram = new StringBuilder();
            for (int j = 0; j < N; j++) {
                gram.append(words[i + j]).append(" ");
            }

            String key = gram.toString().trim();

            ngramMap.putIfAbsent(key, new HashSet<>());
            ngramMap.get(key).add(docId);
        }
    }

    public void analyzeDocument(String docId, String text) {

        HashMap<String, Integer> matchCount = new HashMap<>();
        String[] words = text.split("\\s+");

        int total = 0;

        for (int i = 0; i <= words.length - N; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < N; j++)
                gram.append(words[i + j]).append(" ");

            String key = gram.toString().trim();

            total++;

            if (ngramMap.containsKey(key)) {
                for (String otherDoc : ngramMap.get(key)) {
                    matchCount.put(otherDoc,
                            matchCount.getOrDefault(otherDoc, 0) + 1);
                }
            }
        }

        for (String doc : matchCount.keySet()) {

            double similarity = (matchCount.get(doc) * 100.0) / total;

            System.out.println("Matched with " + doc +
                    " Similarity: " + similarity + "%");
        }
    }

    public static void main(String[] args) {

        Problem4 detector = new Problem4();

        detector.addDocument("essay1",
                "java is a programming language used for software development");

        detector.analyzeDocument("essay2",
                "java is a programming language used widely for development");
    }
}