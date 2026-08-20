class Book {
    private String id;
    private String title;
    private double price;
    private int stock;

    public Book(String id, String title, double price, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public double getTotalValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return String.format("書號: %s | 書名: %-15s | 單價: %7.1f | 庫存: %2d", id, title, price, stock);
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B01", "Java 核心技術", 680.0, 5),
            new Book("B02", "資料結構實務", 550.0, 2),
            new Book("B03", "演算法圖解", 420.0, 8),
            new Book("B04", "系統設計指南", 790.0, 1)
        };

        System.out.println("=== 所有書籍清單 ===");
        double totalInventoryValue = 0;
        Book highestPriceBook = books[0];

        for (Book b : books) {
            System.out.println(b);
            totalInventoryValue += b.getTotalValue();
            if (b.getPrice() > highestPriceBook.getPrice()) {
                highestPriceBook = b;
            }
        }

        System.out.printf("\n庫存總價值: $%.2f\n", totalInventoryValue);
        System.out.println("價格最高書籍: " + highestPriceBook.getTitle() + " ($" + highestPriceBook.getPrice() + ")");

        System.out.println("\n=== 庫存小於或等於 3 的低庫存書籍 ===");
        for (Book b : books) {
            if (b.getStock() <= 3) {
                System.out.println(b);
            }
        }
    }
}