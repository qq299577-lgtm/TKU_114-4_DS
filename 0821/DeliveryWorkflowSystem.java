import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DeliveryWorkflowSystem {

    static class DeliveryOrder {
        private final String orderId;
        private final String address;

        public DeliveryOrder(String orderId, String address) {
            this.orderId = orderId;
            this.address = address;
        }

        public String getOrderId() { return orderId; }
        public String getAddress() { return address; }

        @Override
        public String toString() {
            return "Order{" + orderId + ", " + address + "}";
        }
    }

    private final Map<String, DeliveryOrder> orderMap = new HashMap<>();
    private final Queue<DeliveryOrder> waitingQueue = new LinkedList<>();
    private final Deque<DeliveryOrder> completedStack = new ArrayDeque<>();

    public boolean addOrder(String id, String address) {
        if (orderMap.containsKey(id)) {
            System.out.println("訂單編號已存在: " + id);
            return false;
        }
        DeliveryOrder order = new DeliveryOrder(id, address);
        orderMap.put(id, order);
        waitingQueue.offer(order);
        System.out.println("新增訂單: " + order);
        return true;
    }

    public DeliveryOrder processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("無等待處理訂單");
            return null;
        }
        DeliveryOrder order = waitingQueue.poll();
        completedStack.push(order);
        System.out.println("完成處理: " + order);
        return order;
    }

    public void undoLastProcessed() {
        if (completedStack.isEmpty()) {
            System.out.println("無已完成訂單可撤銷");
            return;
        }
        DeliveryOrder order = completedStack.pop();
        waitingQueue.offer(order);
        System.out.println("撤銷並退回隊列: " + order);
    }

    public DeliveryOrder queryOrder(String id) {
        return orderMap.get(id);
    }

    public void printStats() {
        System.out.println("=== 統計 ===");
        System.out.println("總訂單數: " + orderMap.size());
        System.out.println("等待配送數: " + waitingQueue.size());
        System.out.println("已完成數: " + completedStack.size());
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();

        system.addOrder("ORD-001", "台北市信義區");
        system.addOrder("ORD-002", "新北市板橋區");
        system.addOrder("ORD-001", "重複測試");

        system.processNext();
        system.processNext();
        system.printStats();

        system.undoLastProcessed();
        system.printStats();

        System.out.println("查詢 ORD-002: " + system.queryOrder("ORD-002"));
    }
}