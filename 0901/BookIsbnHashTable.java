public class BookIsbnHashTable {
    static class BookEntry {
        String isbn;
        String title;
        BookEntry next;

        BookEntry(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    private BookEntry[] table;
    private int size;
    private static final double MAX_LOAD_FACTOR = 0.75;

    public BookIsbnHashTable() {
        this(7);
    }

    public BookIsbnHashTable(int capacity) {
        this.table = new BookEntry[capacity];
        this.size = 0;
    }

    private int hash(String isbn, int capacity) {
        return Math.floorMod(isbn.hashCode(), capacity);
    }

    public void put(String isbn, String title) {
        if ((double) (size + 1) / table.length > MAX_LOAD_FACTOR) {
            resize();
        }

        int index = hash(isbn, table.length);
        BookEntry curr = table[index];
        while (curr != null) {
            if (curr.isbn.equals(isbn)) {
                curr.title = title; // 更新
                return;
            }
            curr = curr.next;
        }

        BookEntry newEntry = new BookEntry(isbn, title);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
    }

    public String search(String isbn) {
        int index = hash(isbn, table.length);
        BookEntry curr = table[index];
        while (curr != null) {
            if (curr.isbn.equals(isbn)) return curr.title;
            curr = curr.next;
        }
        return null;
    }

    public boolean delete(String isbn) {
        int index = hash(isbn, table.length);
        BookEntry curr = table[index];
        BookEntry prev = null;

        while (curr != null) {
            if (curr.isbn.equals(isbn)) {
                if (prev == null) {
                    table[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    private void resize() {
        int newCap = table.length * 2 + 1;
        BookEntry[] newTable = new BookEntry[newCap];

        for (int i = 0; i < table.length; i++) {
            BookEntry curr = table[i];
            while (curr != null) {
                BookEntry next = curr.next;
                int newIdx = hash(curr.isbn, newCap);
                curr.next = newTable[newIdx];
                newTable[newIdx] = curr;
                curr = next;
            }
        }
        this.table = newTable;
    }

    public int size() { return size; }
    public double getLoadFactor() { return (double) size / table.length; }

    public void bucketReport() {
        System.out.printf("=== 雜湊表狀態 (大小: %d, 負載因子: %.2f, 桶數: %d) ===\n", size, getLoadFactor(), table.length);
        for (int i = 0; i < table.length; i++) {
            System.out.print("[" + i + "]: ");
            BookEntry curr = table[i];
            while (curr != null) {
                System.out.print("(" + curr.isbn + ": " + curr.title + ") -> ");
                curr = curr.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        BookIsbnHashTable map = new BookIsbnHashTable(3);
        map.put("978-0134685991", "Effective Java");
        map.put("978-0201633610", "Design Patterns");
        map.put("978-0132350884", "Clean Code");

        map.bucketReport();
        System.out.println("搜尋 978-0134685991: " + map.search("978-0134685991"));

        map.put("978-0134685991", "Effective Java 3rd Edition"); // 更新
        map.delete("978-0201633610"); // 刪除
        map.bucketReport();
    }
}