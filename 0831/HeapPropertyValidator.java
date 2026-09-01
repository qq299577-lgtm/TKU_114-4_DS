import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(List<Integer> list) {
        if (list == null) return false;
        int size = list.size();
        if (size <= 1) return true;

        for (int i = 0; i <= (size - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && list.get(i) > list.get(left)) {
                return false;
            }
            if (right < size && list.get(i) > list.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMaxHeap(List<Integer> list) {
        if (list == null) return false;
        int size = list.size();
        if (size <= 1) return true;

        for (int i = 0; i <= (size - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && list.get(i) < list.get(left)) {
                return false;
            }
            if (right < size && list.get(i) < list.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> minHeapList = List.of(10, 15, 20, 40, 50, 100, 25);
        List<Integer> maxHeapList = List.of(100, 50, 40, 10, 15, 20, 25);
        List<Integer> invalidList = List.of(10, 5, 20);

        System.out.println("minHeapList 是否為 Min Heap: " + isMinHeap(minHeapList));
        System.out.println("maxHeapList 是否為 Max Heap: " + isMaxHeap(maxHeapList));
        System.out.println("invalidList 是否為 Min Heap: " + isMinHeap(invalidList));
        System.out.println("null 驗證: " + isMinHeap(null));
        System.out.println("空 List 驗證: " + isMinHeap(List.of()));
    }
}