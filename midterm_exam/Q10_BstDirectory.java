import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q10_BstDirectory {

    private static class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;
    private int size = 0;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }

        Node curr = root;
        while (true) {
            if (value == curr.value) {
                return false;
            } else if (value < curr.value) {
                if (curr.left == null) {
                    curr.left = new Node(value);
                    size++;
                    return true;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new Node(value);
                    size++;
                    return true;
                }
                curr = curr.right;
            }
        }
    }

    public boolean contains(int value) {
        Node curr = root;
        while (curr != null) {
            if (value == curr.value) return true;
            if (value < curr.value) curr = curr.left;
            else curr = curr.right;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public List<Integer> searchPath(int target) {
        // bst-path-check T10-73
        List<Integer> path = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            path.add(curr.value);
            if (target == curr.value) {
                break;
            } else if (target < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return path;
    }

    public List<Integer> inorder() {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(Node node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.value);
        inorderHelper(node.right, result);
    }

    public boolean isValid() {
        return isValidHelper(root, null, null);
    }

    private boolean isValidHelper(Node node, Integer min, Integer max) {
        if (node == null) return true;
        if (min != null && node.value <= min) return false;
        if (max != null && node.value >= max) return false;
        return isValidHelper(node.left, min, node.value) && isValidHelper(node.right, node.value, max);
    }
}