import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalTestReport {
    static class Node {
        String val;
        Node left, right;
        Node(String val) { this.val = val; }
    }

    public static List<String> preOrder(Node root) {
        List<String> res = new ArrayList<>();
        preOrderHelper(root, res);
        return res;
    }

    private static void preOrderHelper(Node root, List<String> res) {
        if (root == null) return;
        res.add(root.val);
        preOrderHelper(root.left, res);
        preOrderHelper(root.right, res);
    }

    public static List<String> inOrder(Node root) {
        List<String> res = new ArrayList<>();
        inOrderHelper(root, res);
        return res;
    }

    private static void inOrderHelper(Node root, List<String> res) {
        if (root == null) return;
        inOrderHelper(root.left, res);
        res.add(root.val);
        inOrderHelper(root.right, res);
    }

    public static List<String> postOrder(Node root) {
        List<String> res = new ArrayList<>();
        postOrderHelper(root, res);
        return res;
    }

    private static void postOrderHelper(Node root, List<String> res) {
        if (root == null) return;
        postOrderHelper(root.left, res);
        postOrderHelper(root.right, res);
        res.add(root.val);
    }

    public static List<String> levelOrder(Node root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            res.add(curr.val);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return res;
    }

    public static void verify(String name, String type, List<String> expected, List<String> actual) {
        boolean match = expected.equals(actual);
        System.out.printf("[%s] %-11s | Expected: %-20s | Actual: %-20s | Match: %s\n",
                name, type, expected.toString(), actual.toString(), match ? "PASS" : "FAIL");
    }

    public static void testCase(String name, Node root, List<String> expPre, List<String> expIn, List<String> expPost, List<String> expLevel) {
        System.out.println("=== Test: " + name + " ===");
        verify(name, "Pre-order", expPre, preOrder(root));
        verify(name, "In-order", expIn, inOrder(root));
        verify(name, "Post-order", expPost, postOrder(root));
        verify(name, "Level-order", expLevel, levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {
        testCase("Empty Tree", null,
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList());

        Node single = new Node("A");
        testCase("Single Node", single,
                Arrays.asList("A"),
                Arrays.asList("A"),
                Arrays.asList("A"),
                Arrays.asList("A"));

        Node leftOnly = new Node("A");
        leftOnly.left = new Node("B");
        leftOnly.left.left = new Node("C");
        testCase("Left Only", leftOnly,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("C", "B", "A"),
                Arrays.asList("C", "B", "A"),
                Arrays.asList("A", "B", "C"));

        Node rightOnly = new Node("A");
        rightOnly.right = new Node("B");
        rightOnly.right.right = new Node("C");
        testCase("Right Only", rightOnly,
                Arrays.asList("A", "B", "C"),
                Arrays.asList("A", "B", "C"),
                Arrays.asList("C", "B", "A"),
                Arrays.asList("A", "B", "C"));

        Node complete = new Node("A");
        complete.left = new Node("B");
        complete.right = new Node("C");
        complete.left.left = new Node("D");
        complete.left.right = new Node("E");
        testCase("Complete Tree", complete,
                Arrays.asList("A", "B", "D", "E", "C"),
                Arrays.asList("D", "B", "E", "A", "C"),
                Arrays.asList("D", "E", "B", "C", "A"),
                Arrays.asList("A", "B", "C", "D", "E"));

        Node irregular = new Node("A");
        irregular.left = new Node("B");
        irregular.left.right = new Node("C");
        irregular.right = new Node("D");
        irregular.right.left = new Node("E");
        testCase("Irregular Tree", irregular,
                Arrays.asList("A", "B", "C", "D", "E"),
                Arrays.asList("B", "C", "A", "E", "D"),
                Arrays.asList("C", "B", "E", "D", "A"),
                Arrays.asList("A", "B", "D", "C", "E"));
    }
}