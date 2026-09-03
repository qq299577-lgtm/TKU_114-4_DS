import java.util.*;

public class NetworkComponents {

    private final Map<String, List<String>> graph = new HashMap<>();

    public void addNode(String u) {
        if (u != null) graph.putIfAbsent(u, new ArrayList<>());
    }

    public void addConnection(String u, String v) {
        if (u == null || v == null) return;
        addNode(u);
        addNode(v);
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public List<Set<String>> findConnectedComponents() {
        List<Set<String>> components = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<String> comp = new TreeSet<>();
                exploreBfs(node, visited, comp);
                components.add(comp);
            }
        }
        return components;
    }

    private void exploreBfs(String start, Set<String> visited, Set<String> comp) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            comp.add(curr);

            for (String neighbor : graph.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public void printReport() {
        System.out.println("=== 網路連通分量分析報告 ===");
        if (graph.isEmpty()) {
            System.out.println("圖為空，無連通分量。");
            return;
        }

        List<Set<String>> components = findConnectedComponents();
        Set<String> largest = Collections.emptySet();

        for (int i = 0; i < components.size(); i++) {
            Set<String> c = components.get(i);
            System.out.printf("分量 #%d (大小: %d): %s\n", i + 1, c.size(), c);
            if (c.size() > largest.size()) {
                largest = c;
            }
        }

        System.out.printf("連通分量總個數: %d | 最大連通分量節點數: %d | 內容: %s\n",
                components.size(), largest.size(), largest);
    }

    public static void main(String[] args) {
        NetworkComponents net = new NetworkComponents();
        net.addConnection("A", "B");
        net.addConnection("B", "C");
        net.addConnection("D", "E");
        net.addNode("F");

        net.printReport();

        System.out.println("\n--- 邊界案例測試 (空圖) ---");
        NetworkComponents emptyNet = new NetworkComponents();
        emptyNet.printReport();
    }
}