import java.util.ArrayList;
import java.util.List;

public class BinaryTreeStructureReport {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static int size(Node root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    public static int height(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static int countLeaves(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static void getLeaves(Node root, List<Integer> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
    }

    public static void printReport(Node root) {
        if (root == null) {
            System.out.println("Tree is Empty.");
            System.out.println("Size: 0, Height: 0, Leaves Count: 0");
            System.out.println("--------------------");
            return;
        }
        System.out.println("Root: " + root.val);
        List<Integer> leaves = new ArrayList<>();
        getLeaves(root, leaves);
        System.out.println("Leaves: " + leaves);
        System.out.println("Size: " + size(root));
        System.out.println("Leaves Count: " + countLeaves(root));
        System.out.println("Height: " + height(root));
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        Node emptyTree = null;
        printReport(emptyTree);

        Node singleNodeTree = new Node(10);
        printReport(singleNodeTree);

        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);
        printReport(tree);
    }
}