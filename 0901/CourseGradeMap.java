import java.util.*;

public class CourseGradeMap {
    private final Map<String, List<Integer>> grades = new HashMap<>();

    public void addGrade(String courseId, int score) {
        grades.computeIfAbsent(courseId, k -> new ArrayList<>()).add(score);
    }

    public double getAverage(String courseId) {
        List<Integer> list = grades.get(courseId);
        if (list == null || list.isEmpty()) return 0.0;
        int sum = 0;
        for (int s : list) sum += s;
        return (double) sum / list.size();
    }

    public int getMaxScore(String courseId) {
        List<Integer> list = grades.get(courseId);
        if (list == null || list.isEmpty()) return -1;
        int max = Integer.MIN_VALUE;
        for (int s : list) max = Math.max(max, s);
        return max;
    }

    public void printSortedReport() {
        List<String> sortedKeys = new ArrayList<>(grades.keySet());
        Collections.sort(sortedKeys);

        System.out.println("=== 課程成績統計報表 ===");
        for (String courseId : sortedKeys) {
            List<Integer> list = grades.get(courseId);
            System.out.printf("課號: %-8s | 成績清單: %-15s | 平均: %-6.2f | 最高分: %d\n",
                    courseId, list, getAverage(courseId), getMaxScore(courseId));
        }
    }

    public static void main(String[] args) {
        CourseGradeMap cgm = new CourseGradeMap();
        cgm.addGrade("CS101", 85);
        cgm.addGrade("CS101", 92);
        cgm.addGrade("MATH201", 78);
        cgm.addGrade("CS101", 79);
        cgm.addGrade("MATH201", 95);
        cgm.addGrade("PHYS101", 88);

        cgm.printSortedReport();
    }
}