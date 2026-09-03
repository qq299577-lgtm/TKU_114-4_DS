import java.util.*;

public class CampusNavigationSystem {

    static class Location {
        String id;
        String name;

        Location(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(" + id + ")";
        }
    }

    private final Map<String, Location> locationMap = new HashMap<>();
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addLocation(String id, String name) {
        if (id == null || name == null) return;
        locationMap.put(id, new Location(id, name));
        adjList.putIfAbsent(id, new ArrayList<>());
    }

    public void addPathway(String idA, String idB) {
        if (idA == null || idB == null) return;
        if (!locationMap.containsKey(idA) || !locationMap.containsKey(idB)) return;
        adjList.get(idA).add(idB);
        adjList.get(idB).add(idA);
    }

    public List<Location> findShortestPath(String startId, String targetId) {
        if (startId == null || targetId == null) return Collections.emptyList();
        if (!locationMap.containsKey(startId) || !locationMap.containsKey(targetId)) return Collections.emptyList();

        if (startId.equals(targetId)) {
            return List.of(locationMap.get(startId));
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startId);
        visited.add(startId);

        boolean reached = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(targetId)) {
                reached = true;
                break;
            }

            for (String neighbor : adjList.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!reached) return Collections.emptyList();

        LinkedList<Location> path = new LinkedList<>();
        String step = targetId;
        while (step != null) {
            path.addFirst(locationMap.get(step));
            step = parent.get(step);
        }

        return path;
    }

    public void printNavigationReport(String fromId, String toId) {
        System.out.printf("導航查詢 [%s -> %s]:\n", fromId, toId);
        List<Location> path = findShortestPath(fromId, toId);
        if (path.isEmpty()) {
            System.out.println("查無路徑或站點不存在。");
        } else {
            System.out.printf("最少步數邊數: %d 邊\n", path.size() - 1);
            System.out.print("導航指示: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + (i == path.size() - 1 ? "" : " -> "));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CampusNavigationSystem nav = new CampusNavigationSystem();
        nav.addLocation("L1", "校門口");
        nav.addLocation("L2", "行政大樓");
        nav.addLocation("L3", "圖書館");
        nav.addLocation("L4", "資工系館");
        nav.addLocation("L5", "體育館");
        nav.addLocation("L6", "學生宿舍");
        nav.addLocation("L7", "生態池");

        nav.addPathway("L1", "L2");
        nav.addPathway("L2", "L3");
        nav.addPathway("L1", "L4");
        nav.addPathway("L4", "L3");
        nav.addPathway("L3", "L5");
        nav.addPathway("L5", "L6");

        nav.printNavigationReport("L1", "L6");

        System.out.println("\n--- 邊界案例測試 ---");
        nav.printNavigationReport("L1", "L1");
        nav.printNavigationReport("L1", "L7");
        nav.printNavigationReport("L1", "L99");
        nav.printNavigationReport(null, "L2");
    }
}