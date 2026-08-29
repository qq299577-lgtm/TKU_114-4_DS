public class RecursiveCallReport {

    public static int sum(int[] data, int index) {
        if (index == data.length) {
            System.out.printf("進入: index=%d, 觸發基本情況, 回傳值=0\n", index);
            return 0;
        }

        int currentValue = data[index];
        System.out.printf("進入: index=%d, 目前值=%d\n", index, currentValue);

        int recursiveResult = sum(data, index + 1);
        int total = currentValue + recursiveResult;

        System.out.printf("返回: index=%d, 目前值=%d, 遞迴結果=%d, 回傳值=%d\n", 
                          index, currentValue, recursiveResult, total);
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== 測試案例 1: 一般陣列 ===");
        int[] arr1 = {2, 4, 6};
        System.out.println("總和: " + sum(arr1, 0));

        System.out.println("\n=== 測試案例 2: 單一元素陣列 ===");
        int[] arr2 = {10};
        System.out.println("總和: " + sum(arr2, 0));

        System.out.println("\n=== 測試案例 3: 空陣列 ===");
        int[] arr3 = {};
        System.out.println("總和: " + sum(arr3, 0));
    }
}