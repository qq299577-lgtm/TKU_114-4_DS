import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q04_ChainedHashTable {

    static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final List<List<Entry>> buckets;
    private final int bucketCount;
    private int size;

    public Q04_ChainedHashTable(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount 必須大於 0");
        }
        this.bucketCount = bucketCount;
        this.buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            this.buckets.add(new LinkedList<>());
        }
        this.size = 0;
    }

    private int getBucketIndex(int key) {
        return Math.floorMod(key, bucketCount);
    }

    public void put(int key, String value) {
        int index = getBucketIndex(key);
        List<Entry> bucket = buckets.get(index);

        for (Entry entry : bucket) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int index = getBucketIndex(key);
        List<Entry> bucket = buckets.get(index);

        for (Entry entry : bucket) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean remove(int key) {
        int index = getBucketIndex(key);
        List<Entry> bucket = buckets.get(index);

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key == key) {
                bucket.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int longestChain() {
        int maxLen = 0;
        for (List<Entry> bucket : buckets) {
            if (bucket.size() > maxLen) {
                maxLen = bucket.size();
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        try {
            new Q04_ChainedHashTable(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        Q04_ChainedHashTable table = new Q04_ChainedHashTable(5);

        table.put(1, "A");
        table.put(6, "B");
        table.put(-4, "C");
        table.put(11, "D");

        System.out.println("Size after 4 puts: " + table.size());
        System.out.println("Longest chain: " + table.longestChain());

        table.put(6, "B_Updated");
        System.out.println("Size after update: " + table.size());
        System.out.println("Get 6: " + table.get(6));
        System.out.println("Get -4: " + table.get(-4));
        System.out.println("Get 99: " + table.get(99));

        System.out.println("Remove 6: " + table.remove(6));
        System.out.println("Remove 6 again: " + table.remove(6));
        System.out.println("Size after remove: " + table.size());
        System.out.println("Longest chain after remove: " + table.longestChain());
    }
}