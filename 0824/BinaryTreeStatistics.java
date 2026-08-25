import java.util.NoSuchElementException;

public class BinaryTreeStatistics {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static int size(Node root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    public static int sum(Node root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    public static int maximum(Node root) {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        int max = root.val;
        if (root.left != null) {
            max = Math.max(max, maximum(root.left));
        }
        if (root.right != null) {
            max = Math.max(max, maximum(root.right));
        }
        return max;
    }

    public static int countLeaves(Node root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static int height(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static boolean contains(Node root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        return contains(root.left, target) || contains(root.right, target);
    }

    public static void main(String[] args) {
        Node empty = null;
        try {
            maximum(empty);
        } catch (NoSuchElementException e) {
            System.out.println("Empty tree maximum throws NoSuchElementException as expected.");
        }

        Node root = new Node(15);
        root.left = new Node(8);
        root.right = new Node(24);
        root.left.left = new Node(3);
        root.left.right = new Node(12);
        root.right.left = new Node(19);
        root.right.right = new Node(30);

        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Maximum: " + maximum(root));
        System.out.println("Leaves: " + countLeaves(root));
        System.out.println("Height: " + height(root));
        System.out.println("Contains 19: " + contains(root, 19));
        System.out.println("Contains 99: " + contains(root, 99));
    }
}