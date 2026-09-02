import java.util.*;

public class CampusMatrixGraph {
    private final String[] vertices;
    private final Map<String, Integer> vertexIndices;
    private final int[][] adjMatrix;
    private int edgeCount;

    public CampusMatrixGraph(String[] names) {
        int n = names.length;
        this.vertices = Arrays.copyOf(names, n);
        this.vertexIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            vertexIndices.put(names[i], i);
        }
        this.adjMatrix = new int[n][n];
        this.edgeCount = 0;
    }

    public boolean addEdge(String u, String v) {
        if (!vertexIndices.containsKey(u) || !vertexIndices.containsKey(v) || u.equals(v)) return false;
        int i = vertexIndices.get(u);
        int j = vertexIndices.get(v);
        if (adjMatrix[i][j] == 1) return false;
        adjMatrix[i][j] = 1;
        adjMatrix[j][i] = 1;
        edgeCount++;
        return true;
    }

    public boolean removeEdge(String u, String v) {
        if (!vertexIndices.containsKey(u) || !vertexIndices.containsKey(v)) return false;
        int i = vertexIndices.get(u);
        int j = vertexIndices.get(v);
        if (adjMatrix[i][j] == 0) return false;
        adjMatrix[i][j] = 0;
        adjMatrix[j][i] = 0;
        edgeCount--;
        return true;
    }

    public int getDegree(String u) {
        if (!vertexIndices.containsKey(u)) return -1;
        int i = vertexIndices.get(u);
        int deg = 0;
        for (int val : adjMatrix[i]) {
            deg += val;
        }
        return deg;
    }

    public List<String> getNeighbors(String u) {
        if (!vertexIndices.containsKey(u)) return Collections.emptyList();
        int i = vertexIndices.get(u);
        List<String> neighbors = new ArrayList<>();
        for (int j = 0; j < vertices.length; j++) {
            if (adjMatrix[i][j] == 1) {
                neighbors.add(vertices[j]);
            }
        }
        return neighbors;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public static void main(String[] args) {
        String[] locations = {"圖書館", "資工系館", "行政大樓", "操場"};
        CampusMatrixGraph g = new CampusMatrixGraph(locations);

        g.addEdge("圖書館", "資工系館");
        g.addEdge("資工系館", "行政大樓");
        g.addEdge("圖書館", "資工系館"); // 重複邊

        System.out.println("總邊數: " + g.getEdgeCount());
        System.out.println("資工系館的度: " + g.getDegree("資工系館"));
        System.out.println("資工系館的鄰居: " + g.getNeighbors("資工系館"));

        g.removeEdge("圖書館", "資工系館");
        System.out.println("刪除邊後邊數: " + g.getEdgeCount());
    }
}