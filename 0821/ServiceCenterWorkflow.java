import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ServiceCenterWorkflow {

    static class ServiceTicket {
        private final String ticketId;
        private final String description;

        public ServiceTicket(String ticketId, String description) {
            this.ticketId = ticketId;
            this.description = description;
        }

        public String getTicketId() { return ticketId; }
        public String getDescription() { return description; }

        @Override
        public String toString() {
            return "[" + ticketId + ": " + description + "]";
        }
    }

    private final Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private final Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private final Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private final Set<String> registeredIds = new HashSet<>();

    public boolean createTicket(String id, String description) {
        if (registeredIds.contains(id)) {
            System.out.println("工單 ID 已存在: " + id);
            return false;
        }
        ServiceTicket ticket = new ServiceTicket(id, description);
        ticketMap.put(id, ticket);
        waitingQueue.offerLast(ticket);
        registeredIds.add(id);
        System.out.println("建立工單: " + ticket);
        return true;
    }

    public ServiceTicket processNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("無等待中工單");
            return null;
        }
        ServiceTicket ticket = waitingQueue.pollFirst();
        completedStack.push(ticket);
        System.out.println("處理完成: " + ticket);
        return ticket;
    }

    public boolean cancelWaiting(String id) {
        if (!ticketMap.containsKey(id)) {
            System.out.println("找不到工單 ID: " + id);
            return false;
        }
        Iterator<ServiceTicket> it = waitingQueue.iterator();
        while (it.hasNext()) {
            ServiceTicket t = it.next();
            if (t.getTicketId().equals(id)) {
                it.remove();
                ticketMap.remove(id);
                registeredIds.remove(id);
                System.out.println("取消未處理工單: " + id);
                return true;
            }
        }
        System.out.println("工單無法取消 (非等待狀態): " + id);
        return false;
    }

    public boolean undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("已完成棧為空，無法撤銷");
            return false;
        }
        ServiceTicket ticket = completedStack.pop();
        waitingQueue.offerFirst(ticket);
        System.out.println("撤銷完成並放回隊列最前: " + ticket);
        return true;
    }

    public ServiceTicket findById(String id) {
        return ticketMap.get(id);
    }

    public void printSummary() {
        System.out.println("\n--- 服務中心狀態 ---");
        System.out.println("等待隊列: " + waitingQueue);
        System.out.println("完成棧: " + completedStack);
        System.out.println("有效工單索引數: " + ticketMap.size() + "\n");
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow workflow = new ServiceCenterWorkflow();

        workflow.processNext();
        workflow.undoLastCompletion();

        workflow.createTicket("TK-01", "網路維修");
        workflow.createTicket("TK-02", "硬體更換");
        workflow.createTicket("TK-03", "帳號解鎖");
        workflow.createTicket("TK-01", "重複測試");

        workflow.cancelWaiting("TK-99");
        workflow.cancelWaiting("TK-02");

        workflow.processNext();
        workflow.createTicket("TK-04", "軟體安裝");
        workflow.processNext();

        workflow.printSummary();

        workflow.undoLastCompletion();
        workflow.undoLastCompletion();
        workflow.undoLastCompletion();

        workflow.printSummary();
    }
}