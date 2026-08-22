import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    public static void runListOperations(List<Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);

        list.add(1, 15);

        int searchVal = 20;
        int foundIndex = list.indexOf(searchVal);

        list.remove(Integer.valueOf(15));

        int sum = 0;
        for (int num : list) {
            sum += num;
        }

        System.out.println("內容: " + list);
        System.out.println("搜尋 " + searchVal + " 索引位置: " + foundIndex);
        System.out.println("元素總和: " + sum);
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 ArrayList ===");
        List<Integer> arrayList = new ArrayList<>();
        runListOperations(arrayList);

        System.out.println("\n=== 測試 LinkedList ===");
        List<Integer> linkedList = new LinkedList<>();
        runListOperations(linkedList);

        System.out.println("\n=== 內部成本差異分析 ===");
        System.out.println("1. 尾端新增: ArrayList 平攤 O(1)；LinkedList 為 O(1)。");
        System.out.println("2. 中間插入/刪除: ArrayList 需搬移後續元素 O(n)；LinkedList 需先遍歷節點 O(n)，再以 O(1) 改指標。");
        System.out.println("3. 隨機存取/搜尋: ArrayList 支援 O(1) 隨機存取；LinkedList 需循序走訪 O(n)。");
        System.out.println("4. 記憶體開銷: LinkedList 每個節點包含前後指標開銷；ArrayList 存在未用滿的預留陣列空間。");
    }
}