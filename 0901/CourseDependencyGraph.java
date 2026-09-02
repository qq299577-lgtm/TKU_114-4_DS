import java.util.*;

public class CourseDependencyGraph {
    private final Set<String> courses = new TreeSet<>();
    private final Map<String, Set<String>> outgoing = new HashMap<>(); // course -> subsequent courses
    private final Map<String, Set<String>> incoming = new HashMap<>(); // course -> prerequisites

    public void addCourse(String course) {
        courses.add(course);
        outgoing.putIfAbsent(course, new TreeSet<>());
        incoming.putIfAbsent(course, new TreeSet<>());
    }

    public void addPrerequisite(String prereq, String course) {
        addCourse(prereq);
        addCourse(course);
        outgoing.get(prereq).add(course);
        incoming.get(course).add(prereq);
    }

    public void printReport() {
        System.out.printf("%-10s %-8s %-8s %-20s %-20s\n", "課程", "入度", "出度", "先決條件 (Prereq)", "後續課程 (Subsequent)");
        for (String c : courses) {
            Set<String> prereqs = incoming.get(c);
            Set<String> subs = outgoing.get(c);
            System.out.printf("%-10s %-8d %-8d %-20s %-20s\n",
                    c, prereqs.size(), subs.size(), prereqs.toString(), subs.toString());
        }
    }

    public static void main(String[] args) {
        CourseDependencyGraph cdg = new CourseDependencyGraph();
        cdg.addPrerequisite("CS101", "CS102");
        cdg.addPrerequisite("CS102", "CS201");
        cdg.addPrerequisite("MATH101", "CS201");
        cdg.addCourse("ART101");

        cdg.printReport();
    }
}