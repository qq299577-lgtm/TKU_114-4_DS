class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id;
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name;
        this.availableCount = Math.max(availableCount, 0);
    }

    public boolean borrowOne() {
        if (this.availableCount > 0) {
            this.availableCount--;
            return true;
        }
        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            this.availableCount += quantity;
        }
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAvailableCount() { return availableCount; }

    @Override
    public String toString() {
        return String.format("設備編號: %s | 名稱: %s | 可借數量: %d", id, name, availableCount);
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment eq1 = new Equipment("EQ01", "投影機", 1);
        Equipment eq2 = new Equipment("", "筆電", -5); // 測試預設值

        System.out.println("=== 初始狀態 ===");
        System.out.println(eq1);
        System.out.println(eq2);

        System.out.println("\n=== 借用測試 ===");
        System.out.println("EQ01 第一次借用: " + (eq1.borrowOne() ? "成功" : "失敗"));
        System.out.println("EQ01 第二次借用: " + (eq1.borrowOne() ? "成功" : "失敗 (庫存不足)"));
        System.out.println("EQ02 借用: " + (eq2.borrowOne() ? "成功" : "失敗 (庫存為0)"));

        System.out.println("\n=== 歸還測試 ===");
        eq1.returnItems(2);
        System.out.println("EQ01 歸還 2 台後: " + eq1);
        eq1.returnItems(-3); // 無效操作
        System.out.println("EQ01 嘗試歸還負數後: " + eq1);
    }
}