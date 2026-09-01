import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {

    static class Product implements Comparable<Product> {
        String id;
        double sales;

        Product(String id, double sales) {
            this.id = id;
            this.sales = sales;
        }

        @Override
        public int compareTo(Product o) {
            if (Double.compare(this.sales, o.sales) != 0) {
                return Double.compare(this.sales, o.sales);
            }
            return o.id.compareTo(this.id);
        }

        @Override
        public String toString() {
            return String.format("[商品:%s | 銷售額:%.1f]", id, sales);
        }
    }

    public static List<Product> getTopK(List<Product> rawInput, int k) {
        if (k <= 0 || rawInput == null || rawInput.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, Double> merged = new HashMap<>();
        for (Product p : rawInput) {
            if (p != null) {
                merged.put(p.id, merged.getOrDefault(p.id, 0.0) + p.sales);
            }
        }

        PriorityQueue<Product> minHeap = new PriorityQueue<>();

        for (Map.Entry<String, Double> entry : merged.entrySet()) {
            Product current = new Product(entry.getKey(), entry.getValue());
            if (minHeap.size() < k) {
                minHeap.offer(current);
            } else if (current.compareTo(minHeap.peek()) > 0) {
                minHeap.poll();
                minHeap.offer(current);
            }
        }

        List<Product> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        List<Product> salesData = List.of(
            new Product("P100", 300.0),
            new Product("P200", 150.0),
            new Product("P100", 200.0),
            new Product("P300", 500.0),
            new Product("P400", 500.0),
            new Product("P500", 50.0)
        );

        List<Product> top3 = getTopK(salesData, 3);
        System.out.println("Top 3 熱銷商品:");
        for (Product p : top3) {
            System.out.println(p);
        }
    }
}