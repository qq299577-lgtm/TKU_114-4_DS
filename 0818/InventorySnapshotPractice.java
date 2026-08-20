import java.util.Arrays;

final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        return Arrays.copyOf(quantities, quantities.length);
    }

    public int totalQuantity() {
        int total = 0;
        for (int q : quantities) {
            total += q;
        }
        return total;
    }

    public int outOfStockCount() {
        int count = 0;
        for (int q : quantities) {
            if (q == 0) {
                count++;
            }
        }
        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        int[] rawData = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-NORTH", rawData);

        System.out.println("倉庫代碼: " + snapshot.getWarehouseId());
        System.out.println("總庫存數量 (預期 8): " + snapshot.totalQuantity());
        System.out.println("缺貨品項數 (預期 2): " + snapshot.outOfStockCount());

        rawData[0] = 999;
        System.out.println("外部陣列修改後，快照總數仍為: " + snapshot.totalQuantity());

        InventorySnapshot emptySnapshot = new InventorySnapshot("WH-EMPTY", null);
        System.out.println("Null 輸入時總數: " + emptySnapshot.totalQuantity());
    }
}