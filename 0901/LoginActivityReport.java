import java.util.*;

public class LoginActivityReport {
    static class LoginRecord {
        String username;
        String ip;

        LoginRecord(String username, String ip) {
            this.username = username;
            this.ip = ip;
        }
    }

    public static void analyzeLogins(List<LoginRecord> logs, int duplicateThreshold) {
        Map<String, Integer> loginCounts = new HashMap<>();
        Map<String, Set<String>> userIps = new HashMap<>();

        for (LoginRecord record : logs) {
            loginCounts.put(record.username, loginCounts.getOrDefault(record.username, 0) + 1);
            userIps.computeIfAbsent(record.username, k -> new HashSet<>()).add(record.ip);
        }

        System.out.println("=== 異常重複登入報告 (門檻 >= " + duplicateThreshold + " 次) ===");
        List<String> sortedUsers = new ArrayList<>(loginCounts.keySet());
        Collections.sort(sortedUsers);

        for (String user : sortedUsers) {
            int count = loginCounts.get(user);
            int ipCount = userIps.get(user).size();
            if (count >= duplicateThreshold) {
                System.out.printf("帳號: %-10s | 總登入次數: %-3d | 不同 IP 數量: %-3d | 狀態: 異常重複登入\n",
                        user, count, ipCount);
            }
        }
    }

    public static void main(String[] args) {
        List<LoginRecord> records = Arrays.asList(
            new LoginRecord("alice", "192.168.1.1"),
            new LoginRecord("bob", "192.168.1.2"),
            new LoginRecord("alice", "192.168.1.3"),
            new LoginRecord("alice", "192.168.1.1"),
            new LoginRecord("charlie", "10.0.0.1"),
            new LoginRecord("alice", "192.168.1.5"),
            new LoginRecord("bob", "192.168.1.2")
        );

        analyzeLogins(records, 3);
    }
}