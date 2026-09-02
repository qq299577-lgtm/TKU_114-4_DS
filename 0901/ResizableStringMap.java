public class ResizableStringMap {
    static class Entry {
        String key;
        String value;
        Entry next;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public ResizableStringMap() {
        this(5);
    }

    public ResizableStringMap(int capacity) {
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private int hash(String key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    public void put(String key, String value) {
        if ((double) (size + 1) / table.length > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key, table.length);
        Entry curr = table[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
    }

    public String get(String key) {
        int index = hash(key, table.length);
        Entry curr = table[index];
        while (curr != null) {
            if (curr.key.equals(key)) return curr.value;
            curr = curr.next;
        }
        return null;
    }

    private void resize() {
        int newCapacity = table.length * 2 + 1;
        Entry[] newTable = new Entry[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Entry curr = table[i];
            while (curr != null) {
                Entry next = curr.next;
                int newIndex = hash(curr.key, newCapacity);
                curr.next = newTable[newIndex];
                newTable[newIndex] = curr;
                curr = next;
            }
        }
        this.table = newTable;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }

    public void printReport() {
        System.out.printf("容量: %d, 大小: %d, 負載因子: %.2f\n", table.length, size, (double) size / table.length);
        for (int i = 0; i < table.length; i++) {
            System.out.print("[" + i + "]: ");
            Entry curr = table[i];
            while (curr != null) {
                System.out.print("(" + curr.key + " => " + curr.value + ") -> ");
                curr = curr.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        ResizableStringMap map = new ResizableStringMap(3);
        map.put("A", "1");
        map.put("B", "2");
        map.printReport();

        map.put("C", "3"); // 觸發擴容: 3 * 2 + 1 = 7
        map.put("D", "4");
        map.put("E", "5");
        map.put("F", "6"); // 觸發擴容: 7 * 2 + 1 = 15
        map.printReport();
    }
}