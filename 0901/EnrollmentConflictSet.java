import java.util.*;

public class EnrollmentConflictSet {
    static class Enrollment {
        String studentId;
        String courseId;

        Enrollment(String studentId, String courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Enrollment)) return false;
            Enrollment that = (Enrollment) o;
            return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }

        @Override
        public String toString() {
            return "(" + studentId + ", " + courseId + ")";
        }
    }

    public static void processEnrollments(List<Enrollment> rawList) {
        Set<Enrollment> seen = new HashSet<>();
        List<Enrollment> duplicates = new ArrayList<>();
        Map<String, Set<String>> studentCourses = new TreeMap<>();
        Map<String, Integer> courseHeadcount = new TreeMap<>();

        for (Enrollment e : rawList) {
            if (!seen.add(e)) {
                duplicates.add(e);
            } else {
                studentCourses.computeIfAbsent(e.studentId, k -> new TreeSet<>()).add(e.courseId);
                courseHeadcount.put(e.courseId, courseHeadcount.getOrDefault(e.courseId, 0) + 1);
            }
        }

        System.out.println("重複選課記錄: " + duplicates);

        System.out.println("\n每人選課清單:");
        for (Map.Entry<String, Set<String>> entry : studentCourses.entrySet()) {
            System.out.println("學生 " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n每門課修課人數:");
        for (Map.Entry<String, Integer> entry : courseHeadcount.entrySet()) {
            System.out.println("課程 " + entry.getKey() + ": " + entry.getValue() + " 人");
        }
    }

    public static void main(String[] args) {
        List<Enrollment> list = Arrays.asList(
            new Enrollment("S01", "CS101"),
            new Enrollment("S02", "CS101"),
            new Enrollment("S01", "MATH101"),
            new Enrollment("S01", "CS101"), // 重複
            new Enrollment("S03", "CS102"),
            new Enrollment("S02", "MATH101")
        );

        processEnrollments(list);
    }
}