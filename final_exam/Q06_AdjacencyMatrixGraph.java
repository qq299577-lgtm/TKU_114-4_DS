import java.util.*;

public class Q06_AdjacencyMatrixGraph {

    private final List<String> vertexOrder = new ArrayList<>();
    private final Map<String, Integer> indexMap = new HashMap<>();
    private final boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        if (vertices != null) {
            for (String v : vertices) {
                if (v != null && !indexMap.containsKey(v)) {
                    indexMap.put(v, vertexOrder.size());
                    vertexOrder.add(v);
                }
            }
        }
        int n = vertexOrder.size();
        this.matrix = new boolean[n][n];
    }

    public boolean addEdge(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }

        Integer i = indexMap.get(first);
        Integer j = indexMap.get(second);

        if (i == null || j == null) {
            return false;
        }

        if (matrix[i][j]) {
            return false;
        }

        matrix[i][j] = true;
        matrix[j][i] = true;
        return true;
    }

    public boolean removeEdge(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }

        Integer i = indexMap.get(first);
        Integer j = indexMap.get(second);

        if (i == null || j == null) {
            return false;
        }

        if (!matrix[i][j]) {
            return false;
        }

        matrix[i][j] = false;
        matrix[j][i] = false;
        return true;
    }

    public boolean hasEdge(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }

        Integer i = indexMap.get(first);
        Integer j = indexMap.get(second);

        if (i == null || j == null) {
            return false;
        }

        return matrix[i][j];
    }

    public int degree(String vertex) {
        Integer i = indexMap.get(vertex);
        if (i == null) {
            return -1;
        }

        int deg = 0;
        for (int j = 0; j < vertexOrder.size(); j++) {
            if (matrix[i][j]) {
                deg++;
            }
        }
        return deg;
    }

    public List<String> neighbors(String vertex) {
        Integer i = indexMap.get(vertex);
        if (i == null) {
            return Collections.emptyList();
        }

        List<String> res = new ArrayList<>();
        for (int j = 0; j < vertexOrder.size(); j++) {
            if (matrix[i][j]) {
                res.add(vertexOrder.get(j));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> v = List.of("A", "B", "C", "D");
        Q06_AdjacencyMatrixGraph g = new Q06_AdjacencyMatrixGraph(v);

        System.out.println(g.addEdge("A", "B"));
        System.out.println(g.addEdge("B", "C"));
        System.out.println(g.addEdge("A", "C"));

        System.out.println(g.addEdge("A", "A"));
        System.out.println(g.addEdge("A", "B"));
        System.out.println(g.addEdge("A", "Z"));
        System.out.println(g.addEdge(null, "B"));

        System.out.println(g.hasEdge("A", "B"));
        System.out.println(g.hasEdge("B", "A"));
        System.out.println(g.hasEdge("B", "D"));
        System.out.println(g.hasEdge("A", "Z"));

        System.out.println(g.degree("B"));
        System.out.println(g.degree("D"));
        System.out.println(g.degree("Z"));

        System.out.println(g.neighbors("B"));
        System.out.println(g.neighbors("D"));
        System.out.println(g.neighbors("Z"));

        System.out.println(g.removeEdge("A", "B"));
        System.out.println(g.removeEdge("A", "B"));
        System.out.println(g.hasEdge("A", "B"));
        System.out.println(g.degree("B"));
    }
}