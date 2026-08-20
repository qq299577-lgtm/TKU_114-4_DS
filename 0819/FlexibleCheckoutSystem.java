interface PricingPolicy {
    double calculateFinalPrice(double originalPrice);
    String getPolicyName();
}

class RegularPrice implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return Math.max(originalPrice, 0);
    }

    @Override
    public String getPolicyName() {
        return "原價計費";
    }
}

class VipDiscount implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return Math.max(originalPrice * 0.85, 0);
    }

    @Override
    public String getPolicyName() {
        return "VIP 85折優惠";
    }
}

class ThresholdDiscount implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        double price = Math.max(originalPrice, 0);
        return price >= 2000 ? price - 300 : price;
    }

    @Override
    public String getPolicyName() {
        return "滿2000折300優惠";
    }
}

interface NotificationChannel {
    boolean sendNotification(String message);
    String getChannelName();
}

class EmailChannel implements NotificationChannel {
    @Override
    public boolean sendNotification(String message) {
        System.out.println("[Email 傳送] " + message);
        return true;
    }

    @Override
    public String getChannelName() {
        return "Email";
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public boolean sendNotification(String message) {
        System.out.println("[SMS 簡訊傳送] " + message);
        return true;
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}

class ConsoleChannel implements NotificationChannel {
    @Override
    public boolean sendNotification(String message) {
        System.out.println("[控制台記錄] " + message);
        return true;
    }

    @Override
    public String getChannelName() {
        return "Console";
    }
}

class CheckoutResult {
    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private boolean notified;

    public CheckoutResult(String orderId, double originalPrice, double finalPrice, boolean notified) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notified = notified;
    }

    @Override
    public String toString() {
        return String.format("結帳結果 [訂單: %s | 原價: $%.1f | 實付: $%.1f | 通知狀態: %s]",
                orderId, originalPrice, finalPrice, (notified ? "成功" : "失敗"));
    }
}

public class FlexibleCheckoutSystem {
    public static CheckoutResult checkout(String orderId, double price, PricingPolicy policy, NotificationChannel channel) {
        double finalPrice = policy.calculateFinalPrice(price);
        String msg = String.format("訂單 %s 結帳完成 (套用:%s)，原價 $%.1f，實付 $%.1f",
                orderId, policy.getPolicyName(), price, finalPrice);
        boolean status = channel.sendNotification(msg);
        return new CheckoutResult(orderId, price, finalPrice, status);
    }

    public static void main(String[] args) {
        PricingPolicy regular = new RegularPrice();
        PricingPolicy vip = new VipDiscount();
        PricingPolicy threshold = new ThresholdDiscount();

        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel console = new ConsoleChannel();

        PricingPolicy[] policies = { regular, vip, threshold };
        NotificationChannel[] channels = { email, sms, console };

        int testNo = 1;
        for (PricingPolicy p : policies) {
            for (NotificationChannel c : channels) {
                if (testNo > 6) break;
                String orderId = String.format("ORD-%03d", testNo);
                double amount = 2500.0;
                System.out.printf("=== 測試案例 %d: %s + %s ===\n", testNo, p.getPolicyName(), c.getChannelName());
                CheckoutResult result = checkout(orderId, amount, p, c);
                System.out.println(result);
                System.out.println();
                testNo++;
            }
        }
    }
}