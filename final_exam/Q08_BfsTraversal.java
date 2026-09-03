import java.util.*;

public class Q08_BfsTraversal {

    public static List<String> bfs(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new ArrayList<>();
        }

        List<String> order = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            order.add(curr);

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return order;
    }

    public static Map<String, Integer> distanceFrom(Map<String, List<String>> graph, String start) {
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new LinkedHashMap<>();
        }

        Map<String, Integer> distMap = new LinkedHashMap<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(start);
        distMap.put(start, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int currDist = distMap.get(curr);

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (neighbor != null && !distMap.containsKey(neighbor)) {
                        distMap.put(neighbor, currDist + 1);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return distMap;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D", "E"));
        graph.put("C", List.of("A", "F"));
        graph.put("D", List.of("B"));
        graph.put("E", List.of("B", "F"));
        graph.put("F", List.of("C", "E"));
        graph.put("G", List.of("H"));
        graph.put("H", List.of("G"));

        System.out.println("BFS order: " + bfs(graph, "A"));
        System.out.println("Distance from A: " + distanceFrom(graph, "A"));

        System.out.println("BFS invalid start: " + bfs(graph, "Z"));
        System.out.println("Distance invalid start: " + distanceFrom(graph, "Z"));

        System.out.println("BFS null graph: " + bfs(null, "A"));
        System.out.println("Distance null start: " + distanceFrom(graph, null));

        Map<String, List<String>> emptyGraph = new HashMap<>();
        System.out.println("BFS empty graph: " + bfs(emptyGraph, "A"));
        System.out.println("Distance empty graph: " + distanceFrom(emptyGraph, "A"));
    }
}