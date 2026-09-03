import java.util.*;

public class MetroTransferPath {

    private final Map<String, List<String>> network = new HashMap<>();

    public void addTrack(String stationA, String stationB) {
        if (stationA == null || stationB == null || stationA.equals(stationB)) return;
        network.computeIfAbsent(stationA, k -> new ArrayList<>()).add(stationB);
        network.computeIfAbsent(stationB, k -> new ArrayList<>()).add(stationA);
    }

    public static class PathResult {
        public final List<String> path;
        public final int edges;

        public PathResult(List<String> path, int edges) {
            this.path = path;
            this.edges = edges;
        }
    }

    public PathResult findFewestStopsPath(String start, String target) {
        if (start == null || target == null || !network.containsKey(start) || !network.containsKey(target)) {
            return new PathResult(Collections.emptyList(), -1);
        }

        if (start.equals(target)) {
            return new PathResult(List.of(start), 0);
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
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

            for (String next : network.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parentMap.put(next, curr);
                    queue.offer(next);
                }
            }
        }

        if (!found) return new PathResult(Collections.emptyList(), -1);

        LinkedList<String> fullPath = new LinkedList<>();
        String step = target;
        while (step != null) {
            fullPath.addFirst(step);
            step = parentMap.get(step);
        }

        return new PathResult(fullPath, fullPath.size() - 1);
    }

    public void printPathReport(String start, String target) {
        PathResult result = findFewestStopsPath(start, target);
        System.out.printf("搭乘路徑 [%s -> %s]: ", start, target);
        if (result.edges < 0) {
            System.out.println("無有效搭乘路徑。");
        } else {
            System.out.printf("最少邊數 (搭乘站數區間): %d 邊 | 路徑: %s\n", result.edges, String.join(" -> ", result.path));
        }
    }

    public static void main(String[] args) {
        MetroTransferPath metro = new MetroTransferPath();
        metro.addTrack("北投", "士林");
        metro.addTrack("士林", "台北車站");
        metro.addTrack("台北車站", "西門");
        metro.addTrack("西門", "中正紀念堂");
        metro.addTrack("台北車站", "中正紀念堂");
        metro.addTrack("南港", "昆陽");

        metro.printPathReport("北投", "中正紀念堂");

        System.out.println("\n--- 邊界案例測試 ---");
        metro.printPathReport("台北車站", "台北車站");
        metro.printPathReport("北投", "南港");
        metro.printPathReport("板橋", "台北車站");
        metro.printPathReport(null, "西門");
    }
}