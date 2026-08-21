import java.util.*;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] rawTags = {"Java", "Backend", "Database", "Java", "Spring", "Backend", "Java", "Web"};

        List<String> tagList = new ArrayList<>(Arrays.asList(rawTags));
        Set<String> tagSet = new LinkedHashSet<>(tagList);
        Map<String, Integer> tagCountMap = new LinkedHashMap<>();

        for (String tag : tagList) {
            tagCountMap.put(tag, tagCountMap.getOrDefault(tag, 0) + 1);
        }

        System.out.println("=== 1. List (保留原始順序與重複值) ===");
        System.out.println(tagList);

        System.out.println("\n=== 2. Set (去重後的不重複標籤清單) ===");
        System.out.println(tagSet);

        System.out.println("\n=== 3. Map (各標籤出現次數統計) ===");
        for (Map.Entry<String, Integer> entry : tagCountMap.entrySet()) {
            System.out.printf("標籤: %-10s | 出現次數: %d\n", entry.getKey(), entry.getValue());
        }
    }
}