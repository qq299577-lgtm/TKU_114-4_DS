import java.util.PriorityQueue;

public class SupportTicketQueue {

    static class Ticket implements Comparable<Ticket> {
        int id;
        int severity;
        int createdOrder;

        Ticket(int id, int severity, int createdOrder) {
            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        @Override
        public int compareTo(Ticket o) {
            if (this.severity != o.severity) {
                return Integer.compare(o.severity, this.severity);
            }
            return Integer.compare(this.createdOrder, o.createdOrder);
        }

        @Override
        public String toString() {
            return id + "|" + severity + "|" + createdOrder;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Ticket> queue = new PriorityQueue<>();

        queue.offer(new Ticket(101, 3, 1));
        queue.offer(new Ticket(102, 5, 2));
        queue.offer(new Ticket(103, 5, 3));
        queue.offer(new Ticket(104, 1, 4));
        queue.offer(new Ticket(105, 3, 5));

        System.out.println("處理順序 (id|severity|createdOrder):");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}