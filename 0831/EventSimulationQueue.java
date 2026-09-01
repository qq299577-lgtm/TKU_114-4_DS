import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    static class SimEvent implements Comparable<SimEvent> {
        int eventId;
        long timestamp;
        String type;
        int sequence;

        SimEvent(int eventId, long timestamp, String type, int sequence) {
            this.eventId = eventId;
            this.timestamp = timestamp;
            this.type = type;
            this.sequence = sequence;
        }

        @Override
        public int compareTo(SimEvent o) {
            if (this.timestamp != o.timestamp) {
                return Long.compare(this.timestamp, o.timestamp);
            }
            return Integer.compare(this.sequence, o.sequence);
        }

        @Override
        public String toString() {
            return String.format("[時間:%4d | 序號:%2d | ID:%d | 類型:%s]", timestamp, sequence, eventId, type);
        }
    }

    private final PriorityQueue<SimEvent> eventQueue = new PriorityQueue<>();
    private final List<String> executionLog = new ArrayList<>();

    public void scheduleEvent(int id, long timestamp, String type, int sequence) {
        eventQueue.offer(new SimEvent(id, timestamp, type, sequence));
    }

    public boolean cancelEvent(int eventId) {
        Iterator<SimEvent> iterator = eventQueue.iterator();
        while (iterator.hasNext()) {
            SimEvent e = iterator.next();
            if (e.eventId == eventId) {
                iterator.remove();
                executionLog.add("取消事件 -> ID:" + eventId);
                return true;
            }
        }
        return false;
    }

    public void runSimulation() {
        while (!eventQueue.isEmpty()) {
            SimEvent current = eventQueue.poll();
            executionLog.add("執行事件 -> " + current);
        }
    }

    public void printExecutionLog() {
        System.out.println("=== 模擬執行日誌 ===");
        for (String log : executionLog) {
            System.out.println(log);
        }
    }

    public static void main(String[] args) {
        EventSimulationQueue simulator = new EventSimulationQueue();

        simulator.scheduleEvent(1, 100, "NETWORK_PING", 1);
        simulator.scheduleEvent(2, 50,  "USER_LOGIN",   1);
        simulator.scheduleEvent(3, 100, "TIMER_EXPIRED", 2);
        simulator.scheduleEvent(4, 80,  "RENDER_FRAME",  1);
        simulator.scheduleEvent(5, 50,  "SENSOR_READ",   2);

        simulator.cancelEvent(4);
        simulator.runSimulation();
        simulator.printExecutionLog();
    }
}