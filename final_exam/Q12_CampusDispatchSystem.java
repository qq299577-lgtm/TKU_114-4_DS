import java.util.*;

public class Q12_CampusDispatchSystem {

    public record Request(String id, String location, int priority, long sequence) {}

    private final Map<String, Set<String>> roadNetwork = new HashMap<>();
    private final Map<String, Request> requestMap = new HashMap<>();
    private final PriorityQueue<Request> requestQueue;

    public Q12_CampusDispatchSystem() {
        Comparator<Request> comp = Comparator
                .comparingInt(Request::priority)
                .thenComparingLong(Request::sequence)
                .thenComparing(Request::id, Comparator.nullsLast(Comparator.naturalOrder()));
        this.requestQueue = new PriorityQueue<>(comp);
    }

    public boolean addLocation(String location) {
        if (location == null || roadNetwork.containsKey(location)) {
            return false;
        }
        roadNetwork.put(location, new HashSet<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null || first.equals(second)) {
            return false;
        }
        if (!roadNetwork.containsKey(first) || !roadNetwork.containsKey(second)) {
            return false;
        }
        if (roadNetwork.get(first).contains(second)) {
            return false;
        }
        roadNetwork.get(first).add(second);
        roadNetwork.get(second).add(first);
        return true;
    }

    public boolean submit(Request request) {
        if (request == null || request.id() == null || request.location() == null) {
            return false;
        }
        if (!roadNetwork.containsKey(request.location())) {
            return false;
        }
        if (requestMap.containsKey(request.id())) {
            return false;
        }
        requestMap.put(request.id(), request);
        requestQueue.offer(request);
        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null || !roadNetwork.containsKey(serviceCenter)) {
            return null;
        }

        Set<String> reachableLocations = getReachableLocations(serviceCenter);
        List<Request> unreachableBuffer = new ArrayList<>();
        Request matched = null;

        while (!requestQueue.isEmpty()) {
            Request candidate = requestQueue.poll();
            if (reachableLocations.contains(candidate.location())) {
                matched = candidate;
                requestMap.remove(matched.id());
                break;
            } else {
                unreachableBuffer.add(candidate);
            }
        }

        for (Request unreached : unreachableBuffer) {
            requestQueue.offer(unreached);
        }

        return matched;
    }

    private Set<String> getReachableLocations(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            Set<String> neighbors = roadNetwork.getOrDefault(curr, Collections.emptySet());
            for (String next : neighbors) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return visited;
    }

    public List<String> route(String start, String target) {
        if (start == null || target == null) {
            return new ArrayList<>();
        }
        if (!roadNetwork.containsKey(start) || !roadNetwork.containsKey(target)) {
            return new ArrayList<>();
        }

        if (start.equals(target)) {
            List<String> singleNodePath = new ArrayList<>();
            singleNodePath.add(start);
            return singleNodePath;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> predecessor = new HashMap<>();
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

            Set<String> neighbors = roadNetwork.getOrDefault(curr, Collections.emptySet());
            for (String next : neighbors) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    predecessor.put(next, curr);
                    queue.offer(next);
                    if (next.equals(target)) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                break;
            }
        }

        if (!found) {
            return new ArrayList<>();
        }

        LinkedList<String> fullPath = new LinkedList<>();
        String step = target;
        while (step != null) {
            fullPath.addFirst(step);
            step = predecessor.get(step);
        }

        return new ArrayList<>(fullPath);
    }

    public int pendingCount() {
        return requestQueue.size();
    }

    public static void main(String[] args) {
        Q12_CampusDispatchSystem system = new Q12_CampusDispatchSystem();

        system.addLocation("Center");
        system.addLocation("Library");
        system.addLocation("Dorm");
        system.addLocation("Gym");
        system.addLocation("IsolatedLab");

        system.addRoad("Center", "Library");
        system.addRoad("Library", "Dorm");
        system.addRoad("Center", "Gym");

        Request r1 = new Request("REQ01", "Dorm", 2, 100L);
        Request r2 = new Request("REQ02", "IsolatedLab", 1, 50L);
        Request r3 = new Request("REQ03", "Library", 1, 200L);
        Request r4 = new Request("REQ04", "Gym", 2, 80L);

        System.out.println(system.submit(r1));
        System.out.println(system.submit(r2));
        System.out.println(system.submit(r3));
        System.out.println(system.submit(r4));
        System.out.println(system.submit(r1));
        System.out.println(system.submit(new Request("REQ05", "UnknownZone", 1, 10L)));
        System.out.println(system.submit(null));

        System.out.println("Pending Count: " + system.pendingCount());

        Request firstDispatched = system.nextReachable("Center");
        System.out.println("Next reachable: " + firstDispatched);

        System.out.println("Pending Count after dispatch: " + system.pendingCount());

        Request secondDispatched = system.nextReachable("Center");
        System.out.println("Next reachable: " + secondDispatched);

        Request thirdDispatched = system.nextReachable("Center");
        System.out.println("Next reachable: " + thirdDispatched);

        Request fourthDispatched = system.nextReachable("Center");
        System.out.println("Next reachable (unreachable only): " + fourthDispatched);

        System.out.println("Pending Count remaining: " + system.pendingCount());

        Request labDispatched = system.nextReachable("IsolatedLab");
        System.out.println("Dispatched from IsolatedLab: " + labDispatched);
        System.out.println("Final Pending Count: " + system.pendingCount());

        System.out.println("Route Center -> Dorm: " + system.route("Center", "Dorm"));
        System.out.println("Route Center -> Center: " + system.route("Center", "Center"));
        System.out.println("Route Center -> IsolatedLab: " + system.route("Center", "IsolatedLab"));
        System.out.println("Route Center -> Missing: " + system.route("Center", "Nowhere"));
        System.out.println("Route null -> Dorm: " + system.route(null, "Dorm"));
    }
}