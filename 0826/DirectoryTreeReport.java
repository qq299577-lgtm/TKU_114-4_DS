import java.util.ArrayList;
import java.util.List;

public class DirectoryTreeReport {

    static class FSNode {
        String name;
        boolean isDirectory;
        long size;
        List<FSNode> children = new ArrayList<>();

        FSNode(String name, long size) {
            this.name = name;
            this.isDirectory = false;
            this.size = size;
        }

        FSNode(String name) {
            this.name = name;
            this.isDirectory = true;
            this.size = 0;
        }

        void addChild(FSNode child) {
            children.add(child);
        }
    }

    static class Stats {
        int totalNodes = 0;
        int fileCount = 0;
        int dirCount = 0;
        long maxFileSize = 0;
        String maxFileName = "";
    }

    public static long calculateSizesAndStats(FSNode node, Stats stats) {
        stats.totalNodes++;
        if (!node.isDirectory) {
            stats.fileCount++;
            if (node.size > stats.maxFileSize) {
                stats.maxFileSize = node.size;
                stats.maxFileName = node.name;
            }
            return node.size;
        }

        stats.dirCount++;
        long totalDirSize = 0;
        for (FSNode child : node.children) {
            totalDirSize += calculateSizesAndStats(child, stats);
        }
        node.size = totalDirSize;
        return totalDirSize;
    }

    public static int getHeight(FSNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (FSNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        return 1 + maxChildHeight;
    }

    public static void main(String[] args) {
        FSNode root = new FSNode("root");
        FSNode bin = new FSNode("bin");
        FSNode home = new FSNode("home");
        FSNode user = new FSNode("user");

        bin.addChild(new FSNode("sh", 500));
        bin.addChild(new FSNode("bash", 1200));

        user.addChild(new FSNode("photo.jpg", 4500));
        user.addChild(new FSNode("notes.txt", 150));
        home.addChild(user);

        root.addChild(bin);
        root.addChild(home);
        root.addChild(new FSNode("config.sys", 80));

        Stats stats = new Stats();
        calculateSizesAndStats(root, stats);

        System.out.println("=== 目錄樹容量與統計報告 ===");
        System.out.println("Root 總容量: " + root.size + " bytes");
        System.out.println("Home 目錄容量: " + home.size + " bytes");
        System.out.println("User 目錄容量: " + user.size + " bytes");
        System.out.println("總節點數: " + stats.totalNodes);
        System.out.println("檔案總數: " + stats.fileCount);
        System.out.println("目錄總數: " + stats.dirCount);
        System.out.println("樹高度: " + getHeight(root));
        System.out.printf("最大檔案: %s (%d bytes)\n", stats.maxFileName, stats.maxFileSize);
    }
}