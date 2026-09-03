import java.util.*;

public class DataStructureDecisionReport {

    public static class Requirement {
        public final int id;
        public final String desc;
        public final String chosenDs;
        public final String reasoning;
        public final String timeComplexity;

        public Requirement(int id, String desc, String chosenDs, String reasoning, String timeComplexity) {
            this.id = id;
            this.desc = desc;
            this.chosenDs = chosenDs;
            this.reasoning = reasoning;
            this.timeComplexity = timeComplexity;
        }
    }

    public static List<Requirement> generateStandardReport() {
        List<Requirement> list = new ArrayList<>();
        list.add(new Requirement(1, "高頻隨機索引存取大量資料", "Array / ArrayList", "記憶體連續配置，支援直接地址偏移計算", "存取: O(1)"));
        list.add(new Requirement(2, "函式遞迴呼叫狀態保存與回溯", "Stack (ArrayDeque)", "具備嚴格 LIFO 後進先出語意，適合歷史追蹤", "Push/Pop: O(1)"));
        list.add(new Requirement(3, "印表機工作排隊與先到先服務", "Queue (LinkedList)", "嚴格遵循 FIFO 先進先出原則，保證任務公平處理", "Offer/Poll: O(1)"));
        list.add(new Requirement(4, "急診室依照傷患危急程度叫號", "PriorityQueue (Min/Max Heap)", "每次皆可在對數時間內取得並彈出最高優先級項目", "Insert/Poll: O(log N)"));
        list.add(new Requirement(5, "大量用戶快速透過唯一身分證號搜尋", "HashMap", "藉由 Hash 映射近乎常數時間直接定位記憶體桶位", "搜尋/寫入: 平均 O(1)"));
        list.add(new Requirement(6, "即時維護隨機數流的中位數", "Two Heaps (Max-Heap + Min-Heap)", "雙堆維護兩半數據，堆頂可在常數時間算出中位數", "插入: O(log N), 查詢: O(1)"));
        list.add(new Requirement(7, "學生分數的區間範圍統計 (80~95分)", "BST / TreeMap (Red-Black Tree)", "底層二元搜尋樹有序，可剪枝高效執行區間過濾", "區間查詢: O(log N + K)"));
        list.add(new Requirement(8, "捷運網路兩站之間最少轉乘站數規劃", "Unweighted Graph + BFS", "無權重圖廣度優先搜尋首次觸碰目標即保證最少邊數", "走訪: O(V + E)"));
        list.add(new Requirement(9, "大學課程擋修先修相依關係拓撲排程", "Directed Graph (Adjacency List)", "以出入度鄰接表有向邊建模，可進行拓撲排序檢驗", "拓撲排序: O(V + E)"));
        list.add(new Requirement(10, "海量文章中的快速單字去重統計", "HashSet", "藉由內部雜湊表實作，不允許重複且能高速比對已存在值", "加入/去重: 平均 O(1)"));
        list.add(new Requirement(11, "高鐵站點里程間具權重成本最短路徑", "Weighted Graph + Dijkstra", "圖結構記錄相鄰權重，搭配優先佇列進行路徑鬆弛", "Dijkstra: O((V+E) log V)"));
        list.add(new Requirement(12, "文字編輯器的 Undo / Redo 操作控制", "Two Stacks", "雙堆疊分別保存歷史操作與被復原操作，操作自然直觀", "Undo/Redo: O(1)"));
        return list;
    }

    public static void displayDecisions(List<Requirement> reqs) {
        if (reqs == null || reqs.isEmpty()) {
            System.out.println("決策需求清單為空。");
            return;
        }
        System.out.printf("%-3s | %-24s | %-24s | %-16s | %s\n", "ID", "業務需求情境", "建議資料結構", "核心複雜度", "架構決策理由");
        System.out.println("-".repeat(110));
        for (Requirement r : reqs) {
            System.out.printf("%-3d | %-24s | %-24s | %-16s | %s\n", r.id, r.desc, r.chosenDs, r.timeComplexity, r.reasoning);
        }
    }

    public static void main(String[] args) {
        List<Requirement> report = generateStandardReport();
        displayDecisions(report);

        System.out.println("\n--- 邊界案例測試 (空集合與 null) ---");
        displayDecisions(Collections.emptyList());
        displayDecisions(null);
    }
}