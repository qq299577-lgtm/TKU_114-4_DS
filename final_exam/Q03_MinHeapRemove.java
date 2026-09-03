import java.util.ArrayList;
import java.util.List;

public class Q03_MinHeapRemove {

    private final List<Integer> heap = new ArrayList<>();

    public Q03_MinHeapRemove(List<Integer> values) {
        if (values != null) {
            for (Integer v : values) {
                if (v != null) {
                    heap.add(v);
                }
            }
            heapify();
        }
    }

    private void heapify() {
        int n = heap.size();
        for (int i = (n - 2) / 2; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    public Integer removeMin() {
        if (heap.isEmpty()) {
            return null;
        }

        int min = heap.get(0);
        int lastVal = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastVal);
            bubbleDown(0);
        }

        return min;
    }

    private void bubbleDown(int index) {
        int n = heap.size();
        while (index * 2 + 1 < n) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = left;

            if (right < n && heap.get(right) < heap.get(left)) {
                smallest = right;
            }

            if (heap.get(smallest) < heap.get(index)) {
                int temp = heap.get(index);
                heap.set(index, heap.get(smallest));
                heap.set(smallest, temp);
                index = smallest;
            } else {
                break;
            }
        }
    }

    public Integer peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    public static void main(String[] args) {
        List<Integer> initData = List.of(50, 30, 20, 15, 10, 8, 16);
        Q03_MinHeapRemove minHeap = new Q03_MinHeapRemove(initData);

        System.out.println("Heapify 後 Snapshot: " + minHeap.snapshot());
        System.out.println("Peek: " + minHeap.peek());

        while (minHeap.size() > 0) {
            System.out.println("RemoveMin: " + minHeap.removeMin() + " -> Snapshot: " + minHeap.snapshot());
        }

        System.out.println("Empty removeMin: " + minHeap.removeMin());
        System.out.println("Empty peek: " + minHeap.peek());

        List<Integer> single = List.of(99);
        Q03_MinHeapRemove singleHeap = new Q03_MinHeapRemove(single);
        System.out.println("Single remove: " + singleHeap.removeMin());
        System.out.println("Single size after remove: " + singleHeap.size());
    }
}