import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Q06_EnrollmentIndex {
    private final Map<String, Set<String>> enrollmentMapR26 = new HashMap<>();

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.isBlank() || studentId == null || studentId.isBlank()) {
            return false;
        }
        Set<String> students = enrollmentMapR26.computeIfAbsent(courseCode, k -> new HashSet<>());
        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.isBlank() || studentId == null || studentId.isBlank()) {
            return false;
        }
        Set<String> students = enrollmentMapR26.get(courseCode);
        if (students == null) {
            return false;
        }
        boolean removed = students.remove(studentId);
        if (removed && students.isEmpty()) {
            enrollmentMapR26.remove(courseCode);
        }
        return removed;
    }

    public int courseSize(String courseCode) {
        if (courseCode == null || courseCode.isBlank()) {
            return 0;
        }
        Set<String> students = enrollmentMapR26.get(courseCode);
        return students == null ? 0 : students.size();
    }

    public List<String> studentsOf(String courseCode) {
        if (courseCode == null || courseCode.isBlank()) {
            return Collections.emptyList();
        }
        Set<String> students = enrollmentMapR26.get(courseCode);
        if (students == null || students.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>(students);
        Collections.sort(list);
        return list;
    }

    public List<String> coursesOf(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            return Collections.emptyList();
        }
        Set<String> courses = new TreeSet<>();
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                courses.add(entry.getKey());
            }
        }
        return new ArrayList<>(courses);
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> sortedMap = new TreeMap<>();
        for (Map.Entry<String, Set<String>> entry : enrollmentMapR26.entrySet()) {
            sortedMap.put(entry.getKey(), entry.getValue().size());
        }
        return sortedMap;
    }
}