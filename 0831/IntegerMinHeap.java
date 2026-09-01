import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntegerMinHeap {
    private final List<Integer> heap = new ArrayList<>();

    public void add(int val) {
        heap.add(val);
        siftUp(heap.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap 為空");
        }
        return heap.get(0);
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap 為空");
        }
        int min = heap.get(0);
        int lastVal = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            siftDown(0);
        }
        return min;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index * 2 + 1 < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = left;

            if (right < size && heap.get(right) < heap.get(left)) {
                smallest = right;
            }

            if (heap.get(smallest) < heap.get(index)) {
                swap(index, smallest);
                index = smallest;
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

    public static void main(String[] args) {
        IntegerMinHeap minHeap = new IntegerMinHeap();
        int[] data = {45, 12, 85, 32, 89, 39, 69, 22};
        for (int v : data) {
            minHeap.add(v);
        }

        System.out.print("依序移除最小值 (驗證非遞減): ");
        int prev = Integer.MIN_VALUE;
        boolean isSorted = true;
        while (!minHeap.isEmpty()) {
            int current = minHeap.removeMin();
            System.out.print(current + " ");
            if (current < prev) {
                isSorted = false;
            }
            prev = current;
        }
        System.out.println("\n排序驗證結果: " + (isSorted ? "通過" : "失敗"));

        try {
            minHeap.peek();
        } catch (NoSuchElementException e) {
            System.out.println("空堆 peek() 正常丟出異常: " + e.getMessage());
        }

        try {
            minHeap.removeMin();
        } catch (NoSuchElementException e) {
            System.out.println("空堆 removeMin() 正常丟出異常: " + e.getMessage());
        }
    }
}