import java.util.ArrayList;
import java.util.List;

public class FolderSizeTree {
    static class FolderNode {
        String name;
        int ownSize;
        FolderNode left, right;

        FolderNode(String name, int ownSize) {
            this.name = name;
            this.ownSize = ownSize;
        }
    }

    static class SubtreeInfo {
        int totalSize;
        FolderNode maxNode;
        int maxSize;

        SubtreeInfo(int totalSize, FolderNode maxNode, int maxSize) {
            this.totalSize = totalSize;
            this.maxNode = maxNode;
            this.maxSize = maxSize;
        }
    }

    public static SubtreeInfo computeSizes(FolderNode root) {
        if (root == null) {
            return new SubtreeInfo(0, null, 0);
        }

        SubtreeInfo leftInfo = computeSizes(root.left);
        SubtreeInfo rightInfo = computeSizes(root.right);

        int currentTotal = root.ownSize + leftInfo.totalSize + rightInfo.totalSize;

        FolderNode maxNode = root;
        int maxSize = currentTotal;

        if (leftInfo.maxNode != null && leftInfo.maxSize > maxSize) {
            maxNode = leftInfo.maxNode;
            maxSize = leftInfo.maxSize;
        }
        if (rightInfo.maxNode != null && rightInfo.maxSize > maxSize) {
            maxNode = rightInfo.maxNode;
            maxSize = rightInfo.maxSize;
        }

        return new SubtreeInfo(currentTotal, maxNode, maxSize);
    }

    public static void findLeafFolders(FolderNode root, List<String> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root.name + " (" + root.ownSize + " KB)");
            return;
        }
        findLeafFolders(root.left, leaves);
        findLeafFolders(root.right, leaves);
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("Root", 100);
        root.left = new FolderNode("Documents", 50);
        root.right = new FolderNode("Media", 200);
        root.left.left = new FolderNode("Work", 300);
        root.left.right = new FolderNode("Personal", 150);
        root.right.left = new FolderNode("Music", 500);
        root.right.right = new FolderNode("Video", 1200);

        SubtreeInfo info = computeSizes(root);
        System.out.println("Total Folder Size: " + info.totalSize + " KB");
        System.out.println("Max Subtree Folder: " + info.maxNode.name + " (" + info.maxSize + " KB)");

        List<String> leaves = new ArrayList<>();
        findLeafFolders(root, leaves);
        System.out.println("Leaf Folders: " + leaves);
    }
}