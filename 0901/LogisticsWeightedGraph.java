import java.util.*;

public class LogisticsWeightedGraph {
    static class Edge {
        String dest;
        double cost;

        Edge(String dest, double cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("%s(%.1f)", dest, cost);
        }
    }

    private final Map<String, List<Edge>> adjList = new HashMap<>();

    public void addVertex(String name) {
        adjList.putIfAbsent(name, new ArrayList<>());
    }

    public boolean addOrUpdateEdge(String from, String to, double cost) {
        if (cost < 0) return false;
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;

        List<Edge> edges = adjList.get(from);
        for (Edge e : edges) {
            if (e.dest.equals(to)) {
                e.cost = cost; // 更新權重
                return true;
            }
        }
        edges.add(new Edge(to, cost)); // 新增邊
        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;
        return adjList.get(from).removeIf(e -> e.dest.equals(to));
    }

    public Double getCost(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return null;
        for (Edge e : adjList.get(from)) {
            if (e.dest.equals(to)) return e.cost;
        }
        return null;
    }

    public void printGraph() {
        System.out.println("=== 物流成本網路 ===");
        for (Map.Entry<String, List<Edge>> entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph net = new LogisticsWeightedGraph();
        net.addVertex("台北倉");
        net.addVertex("台中倉");
        net.addVertex("高雄倉");

        net.addOrUpdateEdge("台北倉", "台中倉", 150.0);
        net.addOrUpdateEdge("台中倉", "高雄倉", 200.0);
        net.addOrUpdateEdge("台北倉", "高雄倉", 400.0);

        // 測試拒絕負權重及不存在頂點
        System.out.println("負權重測試: " + net.addOrUpdateEdge("台北倉", "台中倉", -50.0));
        System.out.println("不存在節點測試: " + net.addOrUpdateEdge("台北倉", "花蓮倉", 100.0));

        net.addOrUpdateEdge("台北倉", "高雄倉", 350.0); // 更新成本
        net.printGraph();

        net.removeEdge("台北倉", "台中倉");
        System.out.println("刪除台北到台中的邊後成本: " + net.getCost("台北倉", "台中倉"));
    }
}