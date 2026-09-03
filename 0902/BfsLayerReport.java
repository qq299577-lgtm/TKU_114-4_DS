import java.util.*;

public class BfsLayerReport {

    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addVertex(String v) {
        if (v == null) return;
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String u, String v) {
        if (u == null || v == null) return;
        addVertex(u);
        addVertex(v);
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public Map<String, Integer> getShortestDistances(String start) {
        Map<String, Integer> distances = new LinkedHashMap<>();
        if (start == null || !adjList.containsKey(start)) {
            return distances;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);

            for (String neighbor : adjList.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distances.put(neighbor, currentDist + 1);
                    queue.offer(neighbor);
                }
            }
        }

        return distances;
    }

    public void printReport(String start) {
        System.out.println("=== BFS 距離分層報告 (起點: " + start + ") ===");
        Map<String, Integer> report = getShortestDistances(start);
        if (report.isEmpty()) {
            System.out.println("起點不存在或圖為空。");
            return;
        }
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            System.out.printf("頂點 %-8s | 最少邊數: %d\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        BfsLayerReport graph = new BfsLayerReport();

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "F");
        graph.addEdge("E", "F");
        graph.addEdge("G", "H");
        graph.printReport("A");

        System.out.println("\n--- 邊界案例測試 ---");
        graph.printReport("Z");
        graph.printReport(null);

        BfsLayerReport emptyGraph = new BfsLayerReport();
        emptyGraph.printReport("A");
    }
}