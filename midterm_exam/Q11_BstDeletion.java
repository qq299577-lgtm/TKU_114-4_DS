import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q11_BstDeletion {

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

    public boolean remove(int value) {
        if (!contains(value)) {
            return false;
        }
        root = removeNode(root, value);
        size--;
        return true;
    }

    private Node removeNode(Node node, int value) {
        if (node == null) return null;

        if (value < node.value) {
            node.left = removeNode(node.left, value);
        } else if (value > node.value) {
            node.right = removeNode(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node successorAuditN11 = findMin(node.right);
            node.value = successorAuditN11.value;
            node.right = removeNode(node.right, successorAuditN11.value);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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