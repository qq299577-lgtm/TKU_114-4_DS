import java.util.*;

public class Q05_StudentHashIndex {

    private final Map<String, Set<String>> studentToCourses = new HashMap<>();
    private final Map<String, Set<String>> courseToStudents = new HashMap<>();
    private int enrollments = 0;

    private String normalize(String str) {
        if (str == null) {
            return null;
        }
        String trimmed = str.trim();
        if (trimmed.isEmpty()) {
            return null;
        }
        return trimmed.toUpperCase();
    }

    public boolean enroll(String studentId, String courseId) {
        String sId = normalize(studentId);
        String cId = normalize(courseId);

        if (sId == null || cId == null) {
            return false;
        }

        Set<String> courses = studentToCourses.computeIfAbsent(sId, k -> new HashSet<>());
        if (courses.contains(cId)) {
            return false;
        }

        courses.add(cId);
        courseToStudents.computeIfAbsent(cId, k -> new HashSet<>()).add(sId);
        enrollments++;
        return true;
    }

    public boolean drop(String studentId, String courseId) {
        String sId = normalize(studentId);
        String cId = normalize(courseId);

        if (sId == null || cId == null) {
            return false;
        }

        Set<String> courses = studentToCourses.get(sId);
        if (courses == null || !courses.contains(cId)) {
            return false;
        }

        courses.remove(cId);
        if (courses.isEmpty()) {
            studentToCourses.remove(sId);
        }

        Set<String> students = courseToStudents.get(cId);
        if (students != null) {
            students.remove(sId);
            if (students.isEmpty()) {
                courseToStudents.remove(cId);
            }
        }

        enrollments--;
        return true;
    }

    public Set<String> coursesOf(String studentId) {
        String sId = normalize(studentId);
        if (sId == null || !studentToCourses.containsKey(sId)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(studentToCourses.get(sId)));
    }

    public Set<String> studentsIn(String courseId) {
        String cId = normalize(courseId);
        if (cId == null || !courseToStudents.containsKey(cId)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(courseToStudents.get(cId)));
    }

    public int enrollmentCount() {
        return enrollments;
    }

    public static void main(String[] args) {
        Q05_StudentHashIndex index = new Q05_StudentHashIndex();

        System.out.println(index.enroll(" s101 ", " cs101 "));
        System.out.println(index.enroll("S101", "CS102"));
        System.out.println(index.enroll("S102", "CS101"));

        System.out.println(index.enroll("s101", "cs101"));
        System.out.println(index.enroll(null, "CS101"));
        System.out.println(index.enroll("S103", "   "));

        System.out.println(index.enrollmentCount());
        System.out.println(index.coursesOf("s101"));
        System.out.println(index.studentsIn("cs101"));

        Set<String> safeCourses = index.coursesOf("S101");
        try {
            safeCourses.add("CS999");
        } catch (UnsupportedOperationException e) {
            System.out.println("Set is unmodifiable");
        }

        System.out.println(index.drop("S101", "CS101"));
        System.out.println(index.drop("S101", "CS102"));
        System.out.println(index.coursesOf("S101"));
        System.out.println(index.enrollmentCount());

        System.out.println(index.drop("S102", "CS101"));
        System.out.println(index.studentsIn("CS101"));
    }
}