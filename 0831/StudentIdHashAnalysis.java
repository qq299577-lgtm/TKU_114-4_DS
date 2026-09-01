import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {

    public static void analyze(int[] studentIds, int bucketCount) {
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int id : studentIds) {
            int index = Math.floorMod(id, bucketCount);
            buckets.get(index).add(id);
        }

        int totalCollisions = 0;
        int maxChain = 0;

        for (int i = 0; i < bucketCount; i++) {
            int count = buckets.get(i).size();
            totalCollisions += Math.max(0, count - 1);
            maxChain = Math.max(maxChain, count);
        }

        double avgChain = (double) studentIds.length / bucketCount;

        System.out.printf("【桶數量: %2d】 總筆數: %d | 總碰撞數: %d | 最大鏈長: %d | 平均鏈長: %.2f\n",
                bucketCount, studentIds.length, totalCollisions, maxChain, avgChain);
    }

    public static void main(String[] args) {
        int[] studentIds = {
            11001, 11002, 11007, 11012, 11017, 
            11022, 11027, 11032, 11005, 11015, 
            11025, 11035, 11045, 11055, 11009
        };

        System.out.println("=== 學號雜湊碰撞分析比較 ===");
        analyze(studentIds, 5);
        analyze(studentIds, 11);
    }
}