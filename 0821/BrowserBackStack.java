import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private final Deque<String> history = new ArrayDeque<>();
    private String currentPage = null;

    public void visit(String url) {
        if (currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;
        System.out.println("訪問: " + url);
    }

    public void back() {
        if (history.isEmpty()) {
            System.out.println("無上一頁可返回");
            return;
        }
        currentPage = history.pop();
        System.out.println("返回至: " + currentPage);
    }

    public String getCurrentPage() {
        return currentPage != null ? currentPage : "無目前頁面";
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        System.out.println("目前頁面: " + browser.getCurrentPage());
        browser.back();

        browser.visit("https://google.com");
        browser.visit("https://github.com");
        browser.visit("https://stackoverflow.com");

        System.out.println("目前頁面: " + browser.getCurrentPage());

        browser.back();
        browser.back();
        System.out.println("目前頁面: " + browser.getCurrentPage());

        browser.back();
        browser.back();
    }
}