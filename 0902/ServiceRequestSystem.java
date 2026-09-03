import java.util.*;

public class ServiceRequestSystem {

    static class Request implements Comparable<Request> {
        String id;
        String title;
        int priority;
        long timestamp;

        Request(String id, String title, int priority, long timestamp) {
            this.id = id;
            this.title = title;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(Request o) {
            if (this.priority != o.priority) {
                return Integer.compare(this.priority, o.priority);
            }
            return Long.compare(this.timestamp, o.timestamp);
        }

        @Override
        public String toString() {
            return String.format("[ID:%s | 優先級:%d | 時間:%d | 標題:%s]", id, priority, timestamp, title);
        }
    }

    private final Map<String, Request> requestMap = new HashMap<>();
    private final PriorityQueue<Request> pq = new PriorityQueue<>();

    public boolean addRequest(String id, String title, int priority, long timestamp) {
        if (id == null || title == null || requestMap.containsKey(id)) {
            return false;
        }
        Request req = new Request(id, title, priority, timestamp);
        requestMap.put(id, req);
        pq.offer(req);
        return true;
    }

    public Request queryById(String id) {
        if (id == null) return null;
        return requestMap.get(id);
    }

    public boolean cancelRequest(String id) {
        if (id == null || !requestMap.containsKey(id)) {
            return false;
        }
        Request req = requestMap.remove(id);
        pq.remove(req);
        return true;
    }

    public Request processNextRequest() {
        while (!pq.isEmpty()) {
            Request next = pq.poll();
            if (requestMap.containsKey(next.id)) {
                requestMap.remove(next.id);
                return next;
            }
        }
        return null;
    }

    public int size() {
        return requestMap.size();
    }

    public static void main(String[] args) {
        ServiceRequestSystem sys = new ServiceRequestSystem();
        sys.addRequest("R01", "網路斷線", 2, 1000);
        sys.addRequest("R02", "主伺服器崩潰", 5, 1020);
        sys.addRequest("R03", "更換印表機碳粉", 1, 1010);
        sys.addRequest("R04", "防火牆被攻擊", 5, 1015);

        System.out.println("查詢 R02: " + sys.queryById("R02"));

        sys.cancelRequest("R02");
        System.out.println("取消 R02 後 Map 查詢: " + sys.queryById("R02"));

        System.out.println("\n開始依優先級處理工單:");
        while (sys.size() > 0) {
            System.out.println("處理: " + sys.processNextRequest());
        }

        System.out.println("\n--- 邊界案例測試 ---");
        System.out.println("空佇列取出: " + sys.processNextRequest());
        System.out.println("取消不存在的工單: " + sys.cancelRequest("R99"));
        System.out.println("重複新增已存在工單: " + sys.addRequest("R01", "測試", 1, 2000));
        System.out.println("傳入 null: " + sys.addRequest(null, null, 0, 0));
    }
}