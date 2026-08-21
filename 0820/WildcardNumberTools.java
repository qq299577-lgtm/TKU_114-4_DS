import java.util.*;

public class WildcardNumberTools {

    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Number num : values) {
            if (num != null) {
                sum += num.doubleValue();
            }
        }
        return sum / values.size();
    }

    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        double max = -Double.MAX_VALUE;
        boolean hasValid = false;
        for (Number num : values) {
            if (num != null) {
                max = Math.max(max, num.doubleValue());
                hasValid = true;
            }
        }
        return hasValid ? max : Double.NaN;
    }

    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(10, 20, 30, 40, 50);
        List<Double> doubleList = Arrays.asList(12.5, 45.2, 88.6, 23.1);
        List<Integer> emptyList = new ArrayList<>();

        System.out.printf("Integer 平均: %.2f | 最大值: %.2f\n", average(intList), maximum(intList));
        System.out.printf("Double 平均: %.2f | 最大值: %.2f\n", average(doubleList), maximum(doubleList));
        System.out.printf("空列表 平均: %.2f | 最大值: %.2f\n", average(emptyList), maximum(emptyList));

        List<Number> numList = new ArrayList<>();
        addRange(numList, 1, 5);
        addRange(numList, 10, 5);
        System.out.println("addRange 結果: " + numList);
    }
}