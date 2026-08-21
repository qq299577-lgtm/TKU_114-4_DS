import java.util.*;

class StoreProduct implements Comparable<StoreProduct> {
    private String id;
    private String name;
    private double price;
    private int stock;

    public StoreProduct(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public int compareTo(StoreProduct other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return String.format("[ID: %s] %-12s | 價格: $%6.1f | 庫存: %2d", id, name, price, stock);
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = Arrays.asList(
            new StoreProduct("P03", "Mouse", 800.0, 15),
            new StoreProduct("P01", "Keyboard", 1500.0, 10),
            new StoreProduct("P04", "Monitor", 6000.0, 5),
            new StoreProduct("P02", "Headset", 1500.0, 10),
            new StoreProduct("P05", "Mousepad", 400.0, 15)
        );

        System.out.println("=== 原始清單 ===");
        products.forEach(System.out::println);

        List<StoreProduct> naturalOrder = new ArrayList<>(products);
        Collections.sort(naturalOrder);
        System.out.println("\n=== 自然排序 (ID 升冪) ===");
        naturalOrder.forEach(System.out::println);

        List<StoreProduct> priceOrder = new ArrayList<>(products);
        priceOrder.sort(Comparator.comparingDouble(StoreProduct::getPrice)
                                  .thenComparing(StoreProduct::getName));
        System.out.println("\n=== 規則一 (價格升冪，同價依名稱) ===");
        priceOrder.forEach(System.out::println);

        List<StoreProduct> stockOrder = new ArrayList<>(products);
        stockOrder.sort(Comparator.comparingInt(StoreProduct::getStock).reversed()
                                  .thenComparing(StoreProduct::getId));
        System.out.println("\n=== 規則二 (庫存降冪，同庫存依 ID) ===");
        stockOrder.forEach(System.out::println);
    }
}