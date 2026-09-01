import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {
    private final List<Integer> heap = new ArrayList<>();

    public void add(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
        System.out.println("加入 " + val + " 後的 Heap 狀態: " + snapshot());
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) > heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public int peekMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap 為空");
        }
        return heap.get(0);
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace maxHeap = new MaxHeapInsertTrace();
        int[] data = {25, 40, 10, 50, 30, 50};

        for (int val : data) {
            maxHeap.add(val);
        }

        System.out.println("最終 Root 節點最大值: " + maxHeap.peekMax());
    }
}