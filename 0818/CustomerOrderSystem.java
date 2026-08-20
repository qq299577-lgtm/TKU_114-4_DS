class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

class OrderItem {
    private String itemName;
    private double unitPrice;
    private int quantity;

    public OrderItem(String itemName, double unitPrice, int quantity) {
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = Math.max(quantity, 0);
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;

    public CustomerOrder(String orderId, Customer customer, OrderItem[] items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = (items != null) ? items : new OrderItem[0];
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            if (item != null) total += item.getSubtotal();
        }
        return total;
    }

    public int getTotalItemQuantity() {
        int totalQty = 0;
        for (OrderItem item : items) {
            if (item != null) totalQty += item.getQuantity();
        }
        return totalQty;
    }

    public void printSummary() {
        System.out.println("====================================");
        System.out.printf("訂單代號: %s\n", orderId);
        System.out.printf("顧客: %s (ID: %s)\n", customer.getName(), customer.getId());
        System.out.println("------------------------------------");
        for (OrderItem item : items) {
            if (item != null) {
                System.out.printf(" - %-12s 數量: %2d  小計: $%.2f\n",
                        item.getItemName(), item.getQuantity(), item.getSubtotal());
            }
        }
        System.out.println("------------------------------------");
        System.out.printf("總件數: %d | 總金額: $%.2f\n", getTotalItemQuantity(), calculateTotalAmount());
        System.out.println("====================================");
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer cust = new Customer("C001", "陳大天");
        OrderItem[] items = {
            new OrderItem("機械鍵盤", 2500.0, 1),
            new OrderItem("電競滑鼠", 1200.0, 2),
            new OrderItem("大滑鼠墊", 300.0, 3)
        };

        CustomerOrder order = new CustomerOrder("ORD-20260818", cust, items);
        order.printSummary();
    }
}