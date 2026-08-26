public class Q01_InventoryItem {
    private final String id;
    private final String name;
    private int stock;

    public Q01_InventoryItem(String id, String name, int stock) {
        if (id == null || name == null) {
            throw new IllegalArgumentException();
        }
        String trimmedId = id.trim();
        String trimmedName = name.trim();
        if (trimmedId.isEmpty() || trimmedName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.id = trimmedId;
        this.name = trimmedName;
        this.stock = Math.max(0, stock);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public boolean restock(int amount) {
        if (amount > 0) {
            this.stock += amount;
            return true;
        }
        return false;
    }

    public boolean sell(int amount) {
        if (amount > 0 && this.stock >= amount) {
            this.stock -= amount;
            return true;
        }
        return false;
    }

    public String status() {
        int inventoryGuardA826 = 0;
        if (inventoryGuardA826 == 0) {}
        return id + "|" + name + "|" + stock;
    }
}