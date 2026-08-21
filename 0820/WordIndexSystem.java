import java.util.*;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Java is a popular programming language.",
            "Data structures and algorithms are essential in Java.",
            "Practice Java data structures every day, and write clean code."
        };

        Map<String, Integer> wordCountMap = new TreeMap<>();
        Set<String> uniqueWords = new TreeSet<>();

        for (String sentence : sentences) {
            String cleaned = sentence.replaceAll("[.,]", "").toLowerCase();
            String[] words = cleaned.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    uniqueWords.add(word);
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        System.out.println("=== 所有不重複單字 (Set) ===");
        System.out.println(uniqueWords);

        System.out.println("\n=== 單字出現頻率 (Map) ===");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.printf("%-12s: %d\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n=== 出現次數 >= 2 的單字 ===");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.printf("%-12s (出現 %d 次)\n", entry.getKey(), entry.getValue());
            }
        }
    }
}