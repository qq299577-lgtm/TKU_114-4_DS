class DigitalWallet {
    private String walletId;
    private String owner;
    private double balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, double initialBalance) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = Math.max(initialBalance, 0);
        this.transactionCount = 0;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            this.transactionCount++;
            return true;
        }
        return false;
    }

    public boolean pay(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            this.transactionCount++;
            return true;
        }
        return false;
    }

    public boolean refund(double amount) {
        if (amount > 0) {
            this.balance += amount;
            this.transactionCount++;
            return true;
        }
        return false;
    }

    public String getWalletId() { return walletId; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
    public int getTransactionCount() { return transactionCount; }

    @Override
    public String toString() {
        return String.format("錢包ID: %s | 擁有者: %s | 餘額: %.2f | 交易次數: %d",
                walletId, owner, balance, transactionCount);
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("W888", "蔡同學", 1000.0);
        System.out.println("初始: " + wallet);

        System.out.println("儲值 500: " + (wallet.deposit(500) ? "成功" : "失敗"));
        System.out.println("付款 300: " + (wallet.pay(300) ? "成功" : "失敗"));
        System.out.println("餘額不足付款 2000: " + (wallet.pay(2000) ? "成功" : "失敗"));
        System.out.println("負數付款 -50: " + (wallet.pay(-50) ? "成功" : "失敗"));
        System.out.println("退款 150: " + (wallet.refund(150) ? "成功" : "失敗"));

        System.out.println("最終狀態: " + wallet);
    }
}