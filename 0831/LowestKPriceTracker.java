import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {

    public static List<Double> findLowestKPrices(List<Double> prices, int k) {
        if (k <= 0 || prices == null || prices.isEmpty()) {
            return Collections.emptyList();
        }

        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (Double price : prices) {
            if (price == null || price < 0) {
                continue;
            }

            if (maxHeap.size() < k) {
                maxHeap.offer(price);
            } else if (price < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(price);
            }
        }

        List<Double> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        List<Double> prices = List.of(99.9, -5.0, 15.5, 49.0, 10.0, 5.0, 20.0, 100.0);

        System.out.println("最低 3 個價格: " + findLowestKPrices(prices, 3));
        System.out.println("k <= 0 測試: " + findLowestKPrices(prices, 0));
        System.out.println("k 超過有效元素數: " + findLowestKPrices(prices, 10));
    }
}