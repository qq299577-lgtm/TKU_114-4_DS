import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] data;
    private int size;

    public ArrayMinHeap() {
        this(10);
    }

    public ArrayMinHeap(int capacity) {
        this.data = new int[Math.max(capacity, 2)];
        this.size = 0;
    }

    public void add(int val) {
        ensureCapacity();
        data[size] = val;
        siftUp(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap 為空");
        }
        return data[0];
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap 為空");
        }
        int min = data[0];
        data[0] = data[size - 1];
        size--;
        if (size > 0) {
            siftDown(0);
        }
        return min;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[index] < data[parent]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (index * 2 + 1 < size) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = left;

            if (right < size && data[right] < data[left]) {
                smallest = right;
            }

            if (data[smallest] < data[index]) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap heap = new ArrayMinHeap(4);
        int[] testData = {50, 23, 88, 12, 9, 34, 76, 5, 62, 18, 99, 4, 15, 67, 3, 29, 81, 44, 7, 55};

        for (int v : testData) {
            heap.add(v);
        }

        System.out.println("插入 20 筆資料後的內部快照 (Size=" + heap.size() + "):");
        System.out.println(Arrays.toString(heap.snapshot()));

        System.out.println("最小值 peek(): " + heap.peek());

        System.out.print("依序取出所有元素: ");
        while (!heap.isEmpty()) {
            System.out.print(heap.removeMin() + " ");
        }
        System.out.println();
    }
}