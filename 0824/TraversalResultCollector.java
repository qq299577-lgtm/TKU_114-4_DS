import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalResultCollector {
    static class Node {
        String val;
        Node left, right;
        Node(String val) { this.val = val; }
    }

    public static List<String> preOrder(Node root) {
        List<String> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private static void preOrderHelper(Node root, List<String> result) {
        if (root == null) return;
        result.add(root.val);
        preOrderHelper(root.left, result);
        preOrderHelper(root.right, result);
    }

    public static List<String> inOrder(Node root) {
        List<String> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    private static void inOrderHelper(Node root, List<String> result) {
        if (root == null) return;
        inOrderHelper(root.left, result);
        result.add(root.val);
        inOrderHelper(root.right, result);
    }

    public static List<String> postOrder(Node root) {
        List<String> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private static void postOrderHelper(Node root, List<String> result) {
        if (root == null) return;
        postOrderHelper(root.left, result);
        postOrderHelper(root.right, result);
        result.add(root.val);
    }

    public static List<String> levelOrder(Node root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            result.add(curr.val);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return result;
    }

    public static void runTest(String name, Node root) {
        System.out.println("=== " + name + " ===");
        System.out.println("PreOrder:   " + preOrder(root));
        System.out.println("InOrder:    " + inOrder(root));
        System.out.println("PostOrder:  " + postOrder(root));
        System.out.println("LevelOrder: " + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {
        Node empty = null;
        runTest("Empty Tree", empty);

        Node single = new Node("A");
        runTest("Single Node", single);

        Node leftSkewed = new Node("A");
        leftSkewed.left = new Node("B");
        leftSkewed.left.left = new Node("C");
        runTest("Left-Skewed Tree", leftSkewed);

        Node complete = new Node("A");
        complete.left = new Node("B");
        complete.right = new Node("C");
        complete.left.left = new Node("D");
        complete.left.right = new Node("E");
        complete.right.left = new Node("F");
        complete.right.right = new Node("G");
        runTest("Complete Tree", complete);
    }
}