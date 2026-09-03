import java.util.*;

public class CoursePlanningGraph {

    private final Set<String> courses = new TreeSet<>();
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addCourse(String course) {
        if (course == null) return;
        courses.add(course);
        adjList.putIfAbsent(course, new ArrayList<>());
    }

    public void addPrerequisite(String prereq, String target) {
        if (prereq == null || target == null) return;
        addCourse(prereq);
        addCourse(target);
        adjList.get(prereq).add(target);
    }

    public boolean canReach(String startCourse, String destCourse) {
        if (startCourse == null || destCourse == null) return false;
        if (!courses.contains(startCourse) || !courses.contains(destCourse)) return false;
        Set<String> visited = new HashSet<>();
        return dfsReach(startCourse, destCourse, visited);
    }

    private boolean dfsReach(String curr, String dest, Set<String> visited) {
        if (curr.equals(dest)) return true;
        visited.add(curr);

        for (String next : adjList.getOrDefault(curr, Collections.emptyList())) {
            if (!visited.contains(next)) {
                if (dfsReach(next, dest, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<List<String>> findAllLearningPaths(String startCourse, String destCourse) {
        List<List<String>> allPaths = new ArrayList<>();
        if (startCourse == null || destCourse == null) return allPaths;
        if (!courses.contains(startCourse) || !courses.contains(destCourse)) return allPaths;

        List<String> currentPath = new ArrayList<>();
        Set<String> visitedInPath = new HashSet<>();
        dfsAllPaths(startCourse, destCourse, visitedInPath, currentPath, allPaths);
        return allPaths;
    }

    private void dfsAllPaths(String curr, String dest, Set<String> visited, List<String> currentPath, List<List<String>> allPaths) {
        visited.add(curr);
        currentPath.add(curr);

        if (curr.equals(dest)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            for (String next : adjList.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(next)) {
                    dfsAllPaths(next, dest, visited, currentPath, allPaths);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
        visited.remove(curr);
    }

    public static void main(String[] args) {
        CoursePlanningGraph planner = new CoursePlanningGraph();
        planner.addPrerequisite("CS101", "CS102");
        planner.addPrerequisite("CS102", "CS201");
        planner.addPrerequisite("MATH101", "CS201");
        planner.addPrerequisite("CS201", "CS301");
        planner.addPrerequisite("CS102", "CS301");
        planner.addCourse("ART101");

        System.out.println("CS101 是否能修到 CS301: " + planner.canReach("CS101", "CS301"));
        System.out.println("ART101 是否能修到 CS301: " + planner.canReach("ART101", "CS301"));

        System.out.println("\n從 CS101 到 CS301 的所有先修修課路徑:");
        List<List<String>> paths = planner.findAllLearningPaths("CS101", "CS301");
        for (List<String> p : paths) {
            System.out.println(String.join(" -> ", p));
        }

        System.out.println("\n--- 邊界案例測試 ---");
        System.out.println("起點或終點為 null 可達性: " + planner.canReach(null, "CS301"));
        System.out.println("不存在課程路徑查詢: " + planner.findAllLearningPaths("CS101", "MATH999"));
        System.out.println("自身到自身路徑: " + planner.findAllLearningPaths("CS101", "CS101"));
    }
}