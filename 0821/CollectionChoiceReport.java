import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CollectionChoiceReport {

    public static void main(String[] args) {
        System.out.println("=== 需求 1: 保留搜尋記錄並允許重複 ===");
        System.out.println("介面: List<String> | 實作: ArrayList<String>");
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java 教學");
        searchHistory.add("Spring Boot");
        searchHistory.add("Java 教學");
        System.out.println("搜尋記錄: " + searchHistory);

        System.out.println("\n=== 需求 2: 儲存不重複的會員號碼 ===");
        System.out.println("介面: Set<String> | 實作: HashSet<String>");
        Set<String> memberIds = new HashSet<>();
        memberIds.add("M101");
        memberIds.add("M102");
        memberIds.add("M101");
        System.out.println("會員號碼: " + memberIds);

        System.out.println("\n=== 需求 3: 以學號查詢成績 ===");
        System.out.println("介面: Map<String, Integer> | 實作: HashMap<String, Integer>");
        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("S001", 95);
        scoreMap.put("S002", 88);
        System.out.println("S001 成績: " + scoreMap.get("S001"));

        System.out.println("\n=== 需求 4: 依照順序處理印刷工作 ===");
        System.out.println("介面: Queue<String> | 實作: LinkedList<String>");
        Queue<String> printJobs = new LinkedList<>();
        printJobs.offer("Doc1.pdf");
        printJobs.offer("Doc2.pdf");
        System.out.println("列印順序: " + printJobs.poll() + " -> " + printJobs.poll());

        System.out.println("\n=== 需求 5: 記錄最近多次操作 ===");
        System.out.println("介面: Deque<String> | 實作: ArrayDeque<String>");
        Deque<String> recentOps = new ArrayDeque<>();
        recentOps.push("點擊首頁");
        recentOps.push("篩選商品");
        recentOps.push("加入購物車");
        System.out.println("最近操作: " + recentOps.pop() + ", 前一次: " + recentOps.pop());
    }
}