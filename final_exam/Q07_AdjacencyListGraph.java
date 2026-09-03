import java.util.*;

public class Q07_AdjacencyListGraph {

    private final Map<String, LinkedHashSet<String>> adjList = new HashMap<>();

    public boolean addVertex(String vertex) {
        if (vertex == null || adjList.containsKey(vertex)) {
            return false;
        }
        adjList.put(vertex, new LinkedHashSet<>());
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (from == null || to == null || from.equals(to)) {
            return false;
        }
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        return adjList.get(from).add(to);
    }

    public boolean removeEdge(String from, String to) {
        if (from == null || to == null) {
            return false;
        }
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        return adjList.get(from).remove(to);
    }

    public List<String> outgoing(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(adjList.get(vertex));
    }

    public int inDegree(String vertex) {
        if (vertex == null || !adjList.containsKey(vertex)) {
            return 0;
        }
        int count = 0;
        for (LinkedHashSet<String> neighbors : adjList.values()) {
            if (neighbors.contains(vertex)) {
                count++;
            }
        }
        return count;
    }

    public int edgeCount() {
        int total = 0;
        for (LinkedHashSet<String> neighbors : adjList.values()) {
            total += neighbors.size();
        }
        return total;
    }

    public static void main(String[] args) {
        Q07_AdjacencyListGraph g = new Q07_AdjacencyListGraph();

        System.out.println(g.addVertex("A"));
        System.out.println(g.addVertex("B"));
        System.out.println(g.addVertex("C"));
        System.out.println(g.addVertex("A"));
        System.out.println(g.addVertex(null));

        System.out.println(g.addEdge("A", "B"));
        System.out.println(g.addEdge("A", "C"));
        System.out.println(g.addEdge("B", "C"));
        System.out.println(g.addEdge("A", "A"));
        System.out.println(g.addEdge("A", "B"));
        System.out.println(g.addEdge("A", "Z"));
        System.out.println(g.addEdge(null, "B"));

        System.out.println(g.outgoing("A"));
        System.out.println(g.outgoing("B"));
        System.out.println(g.outgoing("C"));
        System.out.println(g.outgoing("Z"));
        System.out.println(g.outgoing(null));

        System.out.println(g.inDegree("C"));
        System.out.println(g.inDegree("A"));
        System.out.println(g.inDegree("Z"));
        System.out.println(g.inDegree(null));

        System.out.println(g.edgeCount());

        System.out.println(g.removeEdge("A", "B"));
        System.out.println(g.removeEdge("A", "B"));
        System.out.println(g.outgoing("A"));
        System.out.println(g.inDegree("B"));
        System.out.println(g.edgeCount());
    }
}