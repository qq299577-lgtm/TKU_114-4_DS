import java.util.ArrayList;
import java.util.List;

class Repository<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        if (item != null) {
            items.add(item);
        }
    }

    public T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public void printAll() {
        System.out.println("=== Repository 內容 (共 " + items.size() + " 筆) ===");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("[%d] %s\n", i, items.get(i));
        }
        System.out.println();
    }
}

class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, price=%.1f]", id, name, price);
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        Repository<String> strRepo = new Repository<>();
        strRepo.add("Java");
        strRepo.add("Data Structures");
        strRepo.add("Algorithms");
        strRepo.printAll();

        strRepo.remove("Data Structures");
        System.out.println("移除後第 1 個元素: " + strRepo.get(1));
        strRepo.printAll();

        Repository<Product> prodRepo = new Repository<>();
        Product p1 = new Product("P01", "鍵盤", 1290);
        Product p2 = new Product("P02", "滑鼠", 650);
        prodRepo.add(p1);
        prodRepo.add(p2);
        prodRepo.printAll();
    }
}