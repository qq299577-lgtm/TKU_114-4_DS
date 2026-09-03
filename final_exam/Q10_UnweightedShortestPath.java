import java.util.*;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(
            Map<String, List<String>> graph,
            String start, String target) {

        if (graph == null || start == null || target == null) {
            return new ArrayList<>();
        }

        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return new ArrayList<>();
        }

        if (start.equals(target)) {
            List<String> singleNodePath = new ArrayList<>();
            singleNodePath.add(start);
            return singleNodePath;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (curr.equals(target)) {
                found = true;
                break;
            }

            List<String> neighbors = graph.get(curr);
            if (neighbors != null) {
                for (String next : neighbors) {
                    if (next != null && !visited.contains(next) && graph.containsKey(next)) {
                        visited.add(next);
                        predecessor.put(next, curr);
                        queue.offer(next);

                        if (next.equals(target)) {
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (found) {
                break;
            }
        }

        if (!found) {
            return new ArrayList<>();
        }

        LinkedList<String> path = new LinkedList<>();
        String step = target;
        while (step != null) {
            path.addFirst(step);
            step = predecessor.get(step);
        }

        return path;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D", "E"));
        graph.put("C", List.of("A", "F"));
        graph.put("D", List.of("B", "E"));
        graph.put("E", List.of("B", "D", "F"));
        graph.put("F", List.of("C", "E"));
        graph.put("G", List.of("H"));
        graph.put("H", List.of("G"));

        System.out.println("Shortest A -> F: " + shortestPath(graph, "A", "F"));
        System.out.println("Shortest A -> D: " + shortestPath(graph, "A", "D"));
        System.out.println("Shortest A -> A: " + shortestPath(graph, "A", "A"));

        System.out.println("Shortest A -> G (unreachable): " + shortestPath(graph, "A", "G"));
        System.out.println("Shortest A -> Z (missing): " + shortestPath(graph, "A", "Z"));
        System.out.println("Shortest null -> A: " + shortestPath(graph, null, "A"));
        System.out.println("Shortest A -> null: " + shortestPath(graph, "A", null));
        System.out.println("Shortest on null graph: " + shortestPath(null, "A", "B"));
    }
}