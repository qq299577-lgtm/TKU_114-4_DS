import java.util.*;

class StudentRecord {
    private String studentId;
    private String name;
    private int score;
    private String tag;

    public StudentRecord(String studentId, String name, int score, String tag) {
        this.studentId = studentId;
        this.name = name;
        this.score = Math.max(0, Math.min(score, 100));
        this.tag = (tag == null || tag.trim().isEmpty()) ? "General" : tag.trim();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = Math.max(0, Math.min(score, 100)); }
    public String getTag() { return tag; }

    public String getGradeLevel() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | 分數: %3d | 等級: %s | 標籤: %s", studentId, name, score, getGradeLevel(), tag);
    }
}

public class CourseCollectionManager {
    private List<StudentRecord> recordList = new ArrayList<>();
    private Set<String> registeredIds = new HashSet<>();
    private Map<String, StudentRecord> recordMap = new HashMap<>();

    public boolean addStudent(StudentRecord record) {
        if (record == null || registeredIds.contains(record.getStudentId())) {
            return false;
        }
        recordList.add(record);
        registeredIds.add(record.getStudentId());
        recordMap.put(record.getStudentId(), record);
        return true;
    }

    public boolean updateScore(String studentId, int score) {
        StudentRecord record = recordMap.get(studentId);
        if (record != null) {
            record.setScore(score);
            return true;
        }
        return false;
    }

    public List<StudentRecord> findByTag(String tag) {
        List<StudentRecord> result = new ArrayList<>();
        for (StudentRecord r : recordList) {
            if (r.getTag().equalsIgnoreCase(tag)) {
                result.add(r);
            }
        }
        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> dist = new LinkedHashMap<>();
        dist.put("A", 0);
        dist.put("B", 0);
        dist.put("C", 0);
        dist.put("D", 0);
        dist.put("F", 0);

        for (StudentRecord r : recordList) {
            String level = r.getGradeLevel();
            dist.put(level, dist.get(level) + 1);
        }
        return dist;
    }

    public List<StudentRecord> top(int count) {
        List<StudentRecord> sorted = new ArrayList<>(recordList);
        sorted.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        if (count >= sorted.size()) {
            return sorted;
        }
        return sorted.subList(0, Math.max(0, count));
    }

    public void removeBelow(int minimum) {
        Iterator<StudentRecord> it = recordList.iterator();
        while (it.hasNext()) {
            StudentRecord r = it.next();
            if (r.getScore() < minimum) {
                registeredIds.remove(r.getStudentId());
                recordMap.remove(r.getStudentId());
                it.remove();
            }
        }
    }

    public void printAll() {
        System.out.println("=== 目前學生清單 (List/Map/Set 同步: " + recordList.size() + " 筆) ===");
        for (StudentRecord r : recordList) {
            System.out.println(r);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CourseCollectionManager manager = new CourseCollectionManager();

        StudentRecord[] testData = {
            new StudentRecord("S01", "Alice", 95, "Honors"),
            new StudentRecord("S02", "Bob", 82, ""),
            new StudentRecord("S03", "Charlie", 58, "Remedial"),
            new StudentRecord("S04", "David", 82, "Honors"),
            new StudentRecord("S01", "Alice_Dup", 100, "Honors"),
            new StudentRecord("S05", "Eve", 45, null),
            new StudentRecord("S06", "Frank", 74, "General")
        };

        for (StudentRecord s : testData) {
            boolean success = manager.addStudent(s);
            System.out.printf("新增學生 %s (%s): %s\n", s.getName(), s.getStudentId(), success ? "成功" : "失敗 (學號重複)");
        }
        System.out.println();
        manager.printAll();

        manager.updateScore("S03", 65);
        System.out.println("更新 S03 分數後查詢標籤 'Honors':");
        System.out.println(manager.findByTag("Honors"));

        System.out.println("\n成績等級分佈: " + manager.scoreDistribution());

        System.out.println("\n排名前 3 名學生:");
        manager.top(3).forEach(System.out::println);

        System.out.println("\n執行 removeBelow(60) 移除不及格者...");
        manager.removeBelow(60);
        manager.printAll();
    }
}