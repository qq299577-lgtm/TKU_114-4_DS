class Account {
    private String accountId;
    private String name;
    private int balance;

    public Account(String accountId, String name, int balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = Math.max(balance, 0);
    }

    public String getAccountId() { return accountId; }
    public String getName() { return name; }
    public int getBalance() { return balance; }

    public void deduct(int amount) { this.balance -= amount; }
    public void add(int amount) { this.balance += amount; }

    @Override
    public String toString() {
        return String.format("[%s] %s 餘額: %d", accountId, name, balance);
    }
}

class TransferService {
    public static boolean transfer(Account source, Account target, int amount) {

        if (source == null || target == null) {
            System.out.println("轉帳失敗: 帳戶不可為 null");
            return false;
        }
        if (source == target) {
            System.out.println("轉帳失敗: 不可轉帳給同一帳戶");
            return false;
        }
        if (amount <= 0 || source.getBalance() < amount) {
            System.out.println("轉帳失敗: 金額不合法或餘額不足");
            return false;
        }

        source.deduct(amount);
        target.add(amount);
        System.out.println("轉帳成功: $" + amount);
        return true;
    }
}

public class AccountTransferService {
    public static void main(String[] args) {
        Account acc1 = new Account("A01", "Alice", 1000);
        Account acc2 = new Account("A02", "Bob", 500);

        System.out.println("=== 測試 1: 正常成功轉帳 ===");
        TransferService.transfer(acc1, acc2, 300);
        System.out.println(acc1 + " | " + acc2);

        System.out.println("\n=== 測試 2: 餘額不足 ===");
        TransferService.transfer(acc1, acc2, 1000);

        System.out.println("\n=== 測試 3: 同帳戶轉帳 ===");
        TransferService.transfer(acc1, acc1, 100);

        System.out.println("\n=== 測試 4: Null 目標 ===");
        TransferService.transfer(acc1, null, 100);

        System.out.println("\n最終帳戶狀態: " + acc1 + " | " + acc2);
    }
}