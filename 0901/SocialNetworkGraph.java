import java.util.*;

public class SocialNetworkGraph {
    private final Map<String, Set<String>> adjList = new HashMap<>();

    public void addUser(String user) {
        adjList.putIfAbsent(user, new HashSet<>());
    }

    public boolean addFriendship(String u, String v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v) || u.equals(v)) return false;
        boolean added = adjList.get(u).add(v);
        adjList.get(v).add(u);
        return added;
    }

    public boolean removeFriendship(String u, String v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) return false;
        boolean removed = adjList.get(u).remove(v);
        adjList.get(v).remove(u);
        return removed;
    }

    public Set<String> getCommonFriends(String u, String v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) return Collections.emptySet();
        Set<String> common = new HashSet<>(adjList.get(u));
        common.retainAll(adjList.get(v));
        return common;
    }

    public List<String> getIsolatedUsers() {
        List<String> isolated = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                isolated.add(entry.getKey());
            }
        }
        return isolated;
    }

    public static void main(String[] args) {
        SocialNetworkGraph sn = new SocialNetworkGraph();
        sn.addUser("Alice");
        sn.addUser("Bob");
        sn.addUser("Charlie");
        sn.addUser("David");
        sn.addUser("Eve"); // 孤立

        sn.addFriendship("Alice", "Bob");
        sn.addFriendship("Alice", "Charlie");
        sn.addFriendship("Bob", "Charlie");
        sn.addFriendship("David", "Charlie");

        System.out.println("Alice 與 Bob 的共同好友: " + sn.getCommonFriends("Alice", "Bob"));
        System.out.println("孤立用戶: " + sn.getIsolatedUsers());

        sn.removeFriendship("David", "Charlie");
        System.out.println("解除後孤立用戶: " + sn.getIsolatedUsers());
    }
}