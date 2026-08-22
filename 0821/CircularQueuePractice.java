import java.util.Arrays;

public class CircularQueuePractice {

    static class CircularQueue<T> {
        private final Object[] array;
        private final int capacity;
        private int front = 0;
        private int rear = 0;
        private int size = 0;

        public CircularQueue(int capacity) {
            this.capacity = capacity;
            this.array = new Object[capacity];
        }

        public void enqueue(T item) {
            if (size == capacity) {
                throw new IllegalStateException("Queue is full!");
            }
            array[rear] = item;
            rear = (rear + 1) % capacity;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {
            if (size == 0) {
                throw new IllegalStateException("Queue is empty!");
            }
            T item = (T) array[front];
            array[front] = null;
            front = (front + 1) % capacity;
            size--;
            return item;
        }

        public void printState() {
            System.out.println("內部陣列: " + Arrays.toString(array) + 
                               ", front: " + front + 
                               ", rear: " + rear + 
                               ", size: " + size);
        }
    }

    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.printState();

        queue.dequeue();
        queue.dequeue();
        queue.printState();

        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.printState();

        queue.dequeue();
        queue.enqueue("G");
        queue.printState();

        while (queue.size > 0) {
            System.out.println("取出: " + queue.dequeue());
        }
    }
}