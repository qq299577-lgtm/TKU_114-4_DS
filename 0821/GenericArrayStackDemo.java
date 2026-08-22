public class GenericArrayStackDemo {

    static class ArrayStack<T> {
        private final Object[] data;
        private int top;
        private final int capacity;

        public ArrayStack(int capacity) {
            this.capacity = capacity;
            this.data = new Object[capacity];
            this.top = -1;
        }

        public void push(T value) {
            if (isFull()) {
                throw new IllegalStateException("棧已滿: " + value);
            }
            data[++top] = value;
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("棧為空");
            }
            T val = (T) data[top];
            data[top--] = null;
            return val;
        }

        @SuppressWarnings("unchecked")
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("棧為空");
            }
            return (T) data[top];
        }

        public int size() {
            return top + 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == capacity - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 ArrayStack<String> ===");
        ArrayStack<String> strStack = new ArrayStack<>(3);
        strStack.push("A");
        strStack.push("B");
        strStack.push("C");
        System.out.println("isFull: " + strStack.isFull());
        System.out.println("peek: " + strStack.peek());
        while (!strStack.isEmpty()) {
            System.out.println("pop: " + strStack.pop());
        }

        System.out.println("\n=== 測試 ArrayStack<Integer> ===");
        ArrayStack<Integer> intStack = new ArrayStack<>(3);
        intStack.push(100);
        intStack.push(200);
        System.out.println("size: " + intStack.size());
        System.out.println("pop: " + intStack.pop());
        System.out.println("isEmpty: " + intStack.isEmpty());
    }
}