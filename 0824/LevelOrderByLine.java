import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderByLine {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static void printLevelByLine(Node root) {
        if (root == null) {
            System.out.println("Empty Tree");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + " (Nodes: " + levelSize + "): ");

            for (int i = 0; i < levelSize; i++) {
                Node curr = queue.poll();
                System.out.print(curr.val + " ");
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        Node empty = null;
        printLevelByLine(empty);
        System.out.println("--------------------");

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.left.left.left = new Node(7);
        printLevelByLine(root);
    }
}