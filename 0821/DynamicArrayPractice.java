import java.util.Arrays;

public class DynamicArrayPractice {

    static class DynamicArray<T> {
        private Object[] elements;
        private int size;
        private static final int DEFAULT_CAPACITY = 2;

        public DynamicArray() {
            this.elements = new Object[DEFAULT_CAPACITY];
            this.size = 0;
        }

        public void add(T value) {
            ensureCapacity();
            elements[size++] = value;
        }

        public void add(int index, T value) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
            ensureCapacity();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            checkIndex(index);
            return (T) elements[index];
        }

        @SuppressWarnings("unchecked")
        public T set(int index, T value) {
            checkIndex(index);
            T old = (T) elements[index];
            elements[index] = value;
            return old;
        }

        @SuppressWarnings("unchecked")
        public T remove(int index) {
            checkIndex(index);
            T removed = (T) elements[index];
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(elements, index + 1, elements, index, numMoved);
            }
            elements[--size] = null;
            return removed;
        }

        public int size() { return size; }
        public int capacity() { return elements.length; }

        private void ensureCapacity() {
            if (size == elements.length) {
                int newCap = elements.length * 2;
                elements = Arrays.copyOf(elements, newCap);
            }
        }

        private void checkIndex(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOf(elements, size));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 String DynamicArray ===");
        DynamicArray<String> strArr = new DynamicArray<>();
        strArr.add("A");
        strArr.add("B");
        System.out.println("容量: " + strArr.capacity() + ", 內容: " + strArr);
        strArr.add("C");
        System.out.println("擴容後 容量: " + strArr.capacity() + ", 內容: " + strArr);
        strArr.add(1, "X");
        System.out.println("插入後: " + strArr);
        strArr.remove(1);
        System.out.println("刪除後: " + strArr);

        System.out.println("\n=== 測試 Integer DynamicArray 與邊界例外 ===");
        DynamicArray<Integer> intArr = new DynamicArray<>();
        intArr.add(10);
        intArr.add(20);

        try {
            intArr.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲例外 (index = -1): " + e.getMessage());
        }

        try {
            intArr.remove(intArr.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲例外 (index = size): " + e.getMessage());
        }

        DynamicArray<Double> emptyArr = new DynamicArray<>();
        try {
            emptyArr.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲例外 (空結構刪除): " + e.getMessage());
        }
    }
}