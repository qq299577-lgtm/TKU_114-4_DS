import java.util.*;

public class DirectedReachability {

    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addVertex(String u) {
        if (u != null) adjList.putIfAbsent(u, new ArrayList<>());
    }

    public void addDirectedEdge(String from, String to) {
        if (from == null || to == null) return;
        addVertex(from);
        addVertex(to);
        adjList.get(from).add(to);
    }

    public boolean isReachable(String src, String dest) {
        if (src == null || dest == null) return false;
        if (!adjList.containsKey(src) || !adjList.containsKey(dest)) return false;
        if (src.equals(dest)) return true;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(dest)) return true;

            for (String next : adjList.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    public void queryBatch(List<String[]> pairs) {
        System.out.println("=== 批量可達性查詢 ===");
        if (pairs == null || pairs.isEmpty()) {
            System.out.println("查詢清單為空。");
            return;
        }
        for (String[] pair : pairs) {
            if (pair == null || pair.length < 2) {
                System.out.println("無效查詢對");
                continue;
            }
            boolean reachable = isReachable(pair[0], pair[1]);
            System.out.printf("可達性查詢 %-6s -> %-6s : %s\n", pair[0], pair[1], reachable ? "可達" : "不可達");
        }
    }

    public static void main(String[] args) {
        DirectedReachability graph = new DirectedReachability();
        graph.addDirectedEdge("A", "B");
        graph.addDirectedEdge("B", "C");
        graph.addDirectedEdge("C", "D");
        graph.addDirectedEdge("E", "F");

        List<String[]> queries = Arrays.asList(
            new String[]{"A", "D"},
            new String[]{"D", "A"},
            new String[]{"A", "E"},
            new String[]{"E", "F"}
        );
        graph.queryBatch(queries);

        System.out.println("\n--- 邊界案例測試 ---");
        List<String[]> edgeQueries = Arrays.asList(
            new String[]{"A", "Z"},
            new String[]{"Z", "A"},
            new String[]{null, "B"},
            new String[]{"A", "A"}
        );
        graph.queryBatch(edgeQueries);
        graph.queryBatch(Collections.emptyList());
    }
}