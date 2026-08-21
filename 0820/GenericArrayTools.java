import java.util.Objects;
import java.util.Arrays;

public class GenericArrayTools {

    public static <T> int countMatches(T[] data, T target) {
        if (data == null || data.length == 0) {
            return 0;
        }
        int count = 0;
        for (T item : data) {
            if (Objects.equals(item, target)) {
                count++;
            }
        }
        return count;
    }

    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    public static <T> void swap(T[] data, int first, int second) {
        if (data == null || first < 0 || second < 0 || first >= data.length || second >= data.length) {
            return;
        }
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] words = {"Java", "Python", "Java", "C++", "Java"};
        System.out.println("原始陣列: " + Arrays.toString(words));
        System.out.println("Java 出現次數: " + countMatches(words, "Java"));
        System.out.println("最後一個元素: " + last(words));

        swap(words, 1, 3);
        System.out.println("交換索引 1 與 3: " + Arrays.toString(words));

        Integer[] empty = {};
        System.out.println("空陣列 last: " + last(empty));
        swap(words, -1, 10);
    }
}