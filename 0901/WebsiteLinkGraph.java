import java.util.*;

public class WebsiteLinkGraph {
    private final Set<String> pages = new TreeSet<>();
    private final Map<String, Set<String>> outLinks = new HashMap<>();
    private final Map<String, Integer> inDegreeCount = new HashMap<>();

    public void addPage(String page) {
        pages.add(page);
        outLinks.putIfAbsent(page, new TreeSet<>());
        inDegreeCount.putIfAbsent(page, 0);
    }

    public void addLink(String from, String to) {
        addPage(from);
        addPage(to);
        if (outLinks.get(from).add(to)) {
            inDegreeCount.put(to, inDegreeCount.get(to) + 1);
        }
    }

    public void printReport() {
        System.out.println("=== 網站連結圖報告 ===");
        List<String> noInLinks = new ArrayList<>();
        List<String> noOutLinks = new ArrayList<>();

        for (String page : pages) {
            Set<String> outs = outLinks.get(page);
            int inCount = inDegreeCount.get(page);

            System.out.printf("頁面: %-15s | 傳出連結: %-20s | 傳入計數: %d\n", page, outs, inCount);

            if (inCount == 0) noInLinks.add(page);
            if (outs.isEmpty()) noOutLinks.add(page);
        }

        System.out.println("\n無傳入連結頁面 (No Incoming): " + noInLinks);
        System.out.println("無傳出連結頁面 (Dead Ends): " + noOutLinks);
    }

    public static void main(String[] args) {
        WebsiteLinkGraph g = new WebsiteLinkGraph();
        g.addLink("index.html", "about.html");
        g.addLink("index.html", "products.html");
        g.addLink("products.html", "detail.html");
        g.addPage("orphan.html");

        g.printReport();
    }
}