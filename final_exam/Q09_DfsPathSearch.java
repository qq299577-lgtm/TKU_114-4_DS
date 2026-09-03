import java.util.*;

public class Q09_DfsPathSearch {

    public static List<String> dfs(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new ArrayList<>();
        }
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfsRecursive(graph, start, visited, order);
        return order;
    }

    private static void dfsRecursive(Map<String, List<String>> graph, String curr, Set<String> visited, List<String> order) {
        visited.add(curr);
        order.add(curr);

        List<String> neighbors = graph.get(curr);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (neighbor != null && !visited.contains(neighbor) && graph.containsKey(neighbor)) {
                    dfsRecursive(graph, neighbor, visited, order);
                }
            }
        }
    }

    public static boolean reachable(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || start == null || target == null) {
            return false;
        }
        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return false;
        }
        if (start.equals(target)) {
            return true;
        }
        Set<String> visited = new HashSet<>();
        return reachRecursive(graph, start, target, visited);
    }

    private static boolean reachRecursive(Map<String, List<String>> graph, String curr, String target, Set<String> visited) {
        if (curr.equals(target)) {
            return true;
        }
        visited.add(curr);

        List<String> neighbors = graph.get(curr);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (neighbor != null && !visited.contains(neighbor) && graph.containsKey(neighbor)) {
                    if (reachRecursive(graph, neighbor, target, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D", "E"));
        graph.put("C", List.of("F"));
        graph.put("D", List.of());
        graph.put("E", List.of("F", "A"));
        graph.put("F", List.of());
        graph.put("G", List.of("H"));
        graph.put("H", List.of());

        System.out.println("DFS from A: " + dfs(graph, "A"));

        System.out.println("Reachable A -> F: " + reachable(graph, "A", "F"));
        System.out.println("Reachable A -> G: " + reachable(graph, "A", "G"));
        System.out.println("Reachable A -> A: " + reachable(graph, "A", "A"));

        System.out.println("Reachable A -> Z: " + reachable(graph, "A", "Z"));
        System.out.println("Reachable Z -> A: " + reachable(graph, "Z", "A"));
        System.out.println("Reachable null -> A: " + reachable(graph, null, "A"));
        System.out.println("Reachable A -> null: " + reachable(graph, "A", null));
        System.out.println("DFS invalid start: " + dfs(graph, "Z"));
        System.out.println("DFS null graph: " + dfs(null, "A"));
    }
}