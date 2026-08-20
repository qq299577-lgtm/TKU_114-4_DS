interface DeliveryMethod {
    double calculateShippingCost(double orderAmount);
    String getDeliveryDescription();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public double calculateShippingCost(double orderAmount) {
        return orderAmount >= 1000 ? 0 : 100;
    }

    @Override
    public String getDeliveryDescription() {
        return "黑貓宅急便 (滿千免運，未滿收 $100)";
    }
}

class StorePickup implements DeliveryMethod {
    @Override
    public double calculateShippingCost(double orderAmount) {
        return 60;
    }

    @Override
    public String getDeliveryDescription() {
        return "超商取貨 (固定運費 $60)";
    }
}

class SelfPickup implements DeliveryMethod {
    @Override
    public double calculateShippingCost(double orderAmount) {
        return 0;
    }

    @Override
    public String getDeliveryDescription() {
        return "門市自取 (免運費)";
    }
}

class OrderService {
    private String orderId;
    private double itemAmount;
    private DeliveryMethod deliveryMethod;

    public OrderService(String orderId, double itemAmount, DeliveryMethod deliveryMethod) {
        this.orderId = orderId;
        this.itemAmount = Math.max(itemAmount, 0);
        this.deliveryMethod = deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void printInvoice() {
        double shipping = (deliveryMethod != null) ? deliveryMethod.calculateShippingCost(itemAmount) : 0;
        String desc = (deliveryMethod != null) ? deliveryMethod.getDeliveryDescription() : "未指定物流方式";
        double total = itemAmount + shipping;

        System.out.println("================ 發票明細 ================");
        System.out.println("訂單編號: " + orderId);
        System.out.printf("商品金額: $%.2f\n", itemAmount);
        System.out.println("配送方式: " + desc);
        System.out.printf("運費金額: $%.2f\n", shipping);
        System.out.printf("應付總額: $%.2f\n", total);
        System.out.println("========================================\n");
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        OrderService order1 = new OrderService("ORD-001", 1200, new HomeDelivery());
        OrderService order2 = new OrderService("ORD-002", 800, new HomeDelivery());
        OrderService order3 = new OrderService("ORD-003", 500, new StorePickup());
        OrderService order4 = new OrderService("ORD-004", 3000, new SelfPickup());

        order1.printInvoice();
        order2.printInvoice();
        order3.printInvoice();
        order4.printInvoice();
    }
}