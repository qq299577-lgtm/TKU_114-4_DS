public class IntegerStringHashTable {

    static class Entry {
        int key;
        String value;
        Entry next;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 7;

    public IntegerStringHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public IntegerStringHashTable(int capacity) {
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private int getIndex(int key) {
        return Math.floorMod(key, table.length);
    }

    public void put(int key, String value) {
        int index = getIndex(key);
        Entry current = table[index];

        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
    }

    public String get(int key) {
        int index = getIndex(key);
        Entry current = table[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public boolean remove(int key) {
        int index = getIndex(key);
        Entry current = table[index];
        Entry prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        System.out.println("=== Hash Table 桶結構報告 (Size = " + size + ") ===");
        for (int i = 0; i < table.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("桶 [").append(i).append("]: ");
            Entry curr = table[i];
            while (curr != null) {
                sb.append("(").append(curr.key).append(" => ").append(curr.value).append(") -> ");
                curr = curr.next;
            }
            sb.append("null");
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        IntegerStringHashTable map = new IntegerStringHashTable(5);

        map.put(1, "A");
        map.put(6, "B");
        map.put(11, "C");
        map.put(-4, "D");
        map.put(2, "E");

        map.put(6, "B_Updated");

        map.bucketReport();

        System.out.println("get(6): " + map.get(6));
        System.out.println("containsKey(11): " + map.containsKey(11));

        map.remove(6);
        System.out.println("\n刪除 key=6 後:");
        map.bucketReport();
    }
}