import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    public static void report(int[] keys, int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("桶數量必須大於 0");
        }

        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        if (keys != null) {
            for (int key : keys) {
                int index = Math.floorMod(key, bucketCount);
                buckets.get(index).add(key);
            }
        }

        int totalCollisions = 0;
        System.out.println("=== 碰撞桶分佈報告 (總桶數: " + bucketCount + ") ===");
        for (int i = 0; i < bucketCount; i++) {
            List<Integer> bucket = buckets.get(i);
            int collisions = Math.max(0, bucket.size() - 1);
            totalCollisions += collisions;

            StringBuilder chain = new StringBuilder();
            for (int k : bucket) {
                chain.append(k).append(" -> ");
            }
            chain.append("null");

            System.out.printf("桶 [%2d] (筆數: %2d, 碰撞: %2d): %s\n", i, bucket.size(), collisions, chain.toString());
        }
        System.out.println("總碰撞次數: " + totalCollisions);
    }

    public static void main(String[] args) {
        int[] keys = {10, -5, 20, 15, 30, 20, -15, 7, 17};
        report(keys, 5);

        System.out.println("\n--- 空陣列測試 ---");
        report(new int[]{}, 3);
    }
}