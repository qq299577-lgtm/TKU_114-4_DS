import java.util.*;

public class IterativeDfsTrace {

    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addEdge(String u, String v) {
        if (u == null || v == null) return;
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void traceDfs(String start) {
        System.out.println("=== 迭代式 DFS 追蹤 (起點: " + start + ") ===");
        if (start == null || !adjList.containsKey(start)) {
            System.out.println("起點無效或圖為空，終止追蹤。");
            return;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);
        System.out.printf("[PUSH] %-4s | Stack: %-25s | Visited: %s\n", start, stack, visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.printf("[POP ] %-4s | Stack: %-25s | Visited: %s\n", current, stack, visited);

            if (!visited.contains(current)) {
                visited.add(current);

                List<String> neighbors = new ArrayList<>(adjList.getOrDefault(current, Collections.emptyList()));
                Collections.sort(neighbors, Collections.reverseOrder());

                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                        System.out.printf("[PUSH] %-4s | Stack: %-25s | Visited: %s\n", neighbor, stack, visited);
                    }
                }
            }
        }
        System.out.println("最終走訪完成順序: " + visited);
    }

    public static void main(String[] args) {
        IterativeDfsTrace graph = new IterativeDfsTrace();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");

        graph.traceDfs("A");

        System.out.println("\n--- 邊界案例測試 ---");
        graph.traceDfs("X");
        graph.traceDfs(null);

        IterativeDfsTrace empty = new IterativeDfsTrace();
        empty.traceDfs("A");
    }
}