import java.util.*;

public class MetroMatrixGraph {
    private final String[] stations;
    private final Map<String, Integer> stationMap;
    private final int[][] matrix;

    public MetroMatrixGraph(String[] stations) {
        this.stations = Arrays.copyOf(stations, stations.length);
        this.stationMap = new HashMap<>();
        for (int i = 0; i < stations.length; i++) {
            stationMap.put(stations[i], i);
        }
        this.matrix = new int[stations.length][stations.length];
    }

    public void addTrack(String a, String b) {
        if (!stationMap.containsKey(a) || !stationMap.containsKey(b) || a.equals(b)) return;
        int i = stationMap.get(a);
        int j = stationMap.get(b);
        matrix[i][j] = 1;
        matrix[j][i] = 1;
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < stations.length; i++) {
            for (int j = i + 1; j < stations.length; j++) {
                if (matrix[i][j] == 1) count++;
            }
        }
        return count;
    }

    public int getDegree(String station) {
        if (!stationMap.containsKey(station)) return -1;
        int idx = stationMap.get(station);
        int deg = 0;
        for (int val : matrix[idx]) deg += val;
        return deg;
    }

    public List<String> getNeighbors(String station) {
        if (!stationMap.containsKey(station)) return Collections.emptyList();
        int idx = stationMap.get(station);
        List<String> list = new ArrayList<>();
        for (int j = 0; j < stations.length; j++) {
            if (matrix[idx][j] == 1) list.add(stations[j]);
        }
        return list;
    }

    public void printMatrixReport() {
        System.out.println("=== 捷運路網矩陣 ===");
        System.out.printf("%-10s", "");
        for (String s : stations) System.out.printf("%-8s", s);
        System.out.println();

        for (int i = 0; i < stations.length; i++) {
            System.out.printf("%-10s", stations[i]);
            for (int j = 0; j < stations.length; j++) {
                System.out.printf("%-8d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("總路軌段數 (邊數): " + getEdgeCount());
    }

    public static void main(String[] args) {
        String[] stations = {"台北車站", "中山", "雙連", "西門"};
        MetroMatrixGraph metro = new MetroMatrixGraph(stations);

        metro.addTrack("台北車站", "中山");
        metro.addTrack("中山", "雙連");
        metro.addTrack("台北車站", "西門");

        metro.printMatrixReport();
        System.out.println("台北車站 鄰站: " + metro.getNeighbors("台北車站"));
        System.out.println("台北車站 度數: " + metro.getDegree("台北車站"));
    }
}