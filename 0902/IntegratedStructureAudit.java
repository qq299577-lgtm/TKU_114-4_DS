import java.util.*;

public class IntegratedStructureAudit {

    public enum DataStructureType {
        LIST, QUEUE, BST, HEAP, HASH_TABLE, GRAPH
    }

    public static class AuditTarget {
        String useCase;
        DataStructureType chosen;
        String frequencyPattern;
        boolean orderedRequired;
        boolean relationshipBased;

        public AuditTarget(String useCase, DataStructureType chosen, String frequencyPattern, boolean orderedRequired, boolean relationshipBased) {
            this.useCase = useCase;
            this.chosen = chosen;
            this.frequencyPattern = frequencyPattern;
            this.orderedRequired = orderedRequired;
            this.relationshipBased = relationshipBased;
        }
    }

    public static class Diagnosis {
        public final boolean isOptimal;
        public final String advice;
        public final DataStructureType betterChoice;

        public Diagnosis(boolean isOptimal, String advice, DataStructureType betterChoice) {
            this.isOptimal = isOptimal;
            this.advice = advice;
            this.betterChoice = betterChoice;
        }
    }

    public static Diagnosis audit(AuditTarget target) {
        if (target == null) {
            return new Diagnosis(false, "測試目標為空，無法評估", null);
        }

        if (target.relationshipBased && target.chosen != DataStructureType.GRAPH) {
            return new Diagnosis(false, "實體具備複雜多對多/相依關係，應使用 Graph 建模避免資訊割裂", DataStructureType.GRAPH);
        }

        if (target.frequencyPattern.contains("高頻隨機查詢") && !target.orderedRequired && target.chosen != DataStructureType.HASH_TABLE) {
            return new Diagnosis(false, "無排序需求且要求常數時間查詢，應使用 Hash Table 替代樹或線性串列", DataStructureType.HASH_TABLE);
        }

        if (target.orderedRequired && target.chosen == DataStructureType.HASH_TABLE) {
            return new Diagnosis(false, "Hash Table 無法維護元素順序，無法高效支援區間範圍或排序走訪，應改用 BST", DataStructureType.BST);
        }

        if (target.frequencyPattern.contains("持續取得極值") && target.chosen == DataStructureType.LIST) {
            return new Diagnosis(false, "List 每次找極值需 O(N) 或重排序 O(N log N)，應採用 Heap 維護 O(1) 取極值與 O(log N) 調整", DataStructureType.HEAP);
        }

        if (target.frequencyPattern.contains("排隊先到先審") && target.chosen != DataStructureType.QUEUE) {
            return new Diagnosis(false, "任務具備嚴格先進先出特性，應使用 Queue 保證 O(1) 出入隊列", DataStructureType.QUEUE);
        }

        return new Diagnosis(true, "架構設計合理，符合時間複雜度與業務語意需求", target.chosen);
    }

    public static void main(String[] args) {
        List<AuditTarget> targets = Arrays.asList(
            new AuditTarget("學生學號快速檢索", DataStructureType.LIST, "高頻隨機查詢", false, false),
            new AuditTarget("醫院急診依等級叫號", DataStructureType.HEAP, "持續取得極值", true, false),
            new AuditTarget("商城商品價格區間搜尋", DataStructureType.HASH_TABLE, "區間走訪", true, false),
            new AuditTarget("校園地圖路徑規劃", DataStructureType.LIST, "節點相鄰搜尋", false, true),
            new AuditTarget("伺服器請求依序處理", DataStructureType.QUEUE, "排隊先到先審", false, false),
            new AuditTarget("身分證號黑名單過濾", DataStructureType.HASH_TABLE, "高頻隨機查詢", false, false)
        );

        System.out.printf("%-20s | %-12s | %-8s | %-12s | %s\n", "業務場景", "選用結構", "評估結果", "建議結構", "診斷建議");
        System.out.println("-".repeat(100));

        for (AuditTarget t : targets) {
            Diagnosis d = audit(t);
            System.out.printf("%-20s | %-12s | %-8s | %-12s | %s\n",
                    t.useCase, t.chosen, d.isOptimal ? "合理" : "不合理", d.betterChoice, d.advice);
        }

        System.out.println("\n--- 邊界案例測試 (null 傳入) ---");
        Diagnosis nullDiag = audit(null);
        System.out.println("診斷結果: " + nullDiag.advice);
    }
}