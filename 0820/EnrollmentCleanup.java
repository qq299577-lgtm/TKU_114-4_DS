import java.util.*;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> enrollments = new ArrayList<>(Arrays.asList(
            "Alice", "", "Bob", null, "Charlie", "Alice", "   ", "David", "Bob", null, "Eve"
        ));

        System.out.println("=== 清理前名單 ===");
        System.out.println(enrollments);

        Iterator<String> iterator = enrollments.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        System.out.println("\n=== 清理無效資料後名單 ===");
        System.out.println(enrollments);

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new LinkedHashSet<>();
        for (String name : enrollments) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("\n=== 重複報名者名單 ===");
        System.out.println(duplicates.isEmpty() ? "無重複名單" : duplicates);
    }
}