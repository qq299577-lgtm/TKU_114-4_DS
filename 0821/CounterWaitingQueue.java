import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private final String id;
    private final String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Customer{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}

public class CounterWaitingQueue {
    private final Deque<Customer> queue = new ArrayDeque<>();

    public void addCustomer(Customer customer) {
        queue.offerLast(customer);
        System.out.println("顧客排隊: " + customer);
    }

    public Customer peekNext() {
        if (queue.isEmpty()) {
            System.out.println("目前隊列為空");
            return null;
        }
        return queue.peekFirst();
    }

    public Customer serveNext() {
        if (queue.isEmpty()) {
            System.out.println("隊列為空，無顧客可服務");
            return null;
        }
        Customer c = queue.pollFirst();
        System.out.println("正在服務: " + c);
        return c;
    }

    public int getWaitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        counter.serveNext();
        System.out.println("下一位: " + counter.peekNext());

        counter.addCustomer(new Customer("C01", "Alice"));
        counter.addCustomer(new Customer("C02", "Bob"));
        counter.addCustomer(new Customer("C03", "Charlie"));

        System.out.println("等候人數: " + counter.getWaitingCount());
        System.out.println("查看下一位: " + counter.peekNext());

        counter.serveNext();
        System.out.println("等候人數: " + counter.getWaitingCount());
        counter.serveNext();
        counter.serveNext();

        counter.serveNext();
    }
}