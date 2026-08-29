import java.util.ArrayList;
import java.util.List;

public class BstRangeStatistics {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static class BST {
        Node root;

        void insert(int val) {
            root = insertRec(root, val);
        }

        private Node insertRec(Node node, int val) {
            if (node == null) return new Node(val);
            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            return node;
        }

        List<Integer> valuesBetween(int low, int high) {
            List<Integer> res = new ArrayList<>();
            if (low > high) return res;
            collectValues(root, low, high, res);
            return res;
        }

        private void collectValues(Node node, int low, int high, List<Integer> res) {
            if (node == null) return;
            if (node.val > low) collectValues(node.left, low, high, res);
            if (node.val >= low && node.val <= high) res.add(node.val);
            if (node.val < high) collectValues(node.right, low, high, res);
        }

        int countBetween(int low, int high) {
            if (low > high) return 0;
            return countRec(root, low, high);
        }

        private int countRec(Node node, int low, int high) {
            if (node == null) return 0;
            int count = 0;
            if (node.val >= low && node.val <= high) count++;
            if (node.val > low) count += countRec(node.left, low, high);
            if (node.val < high) count += countRec(node.right, low, high);
            return count;
        }

        int sumBetween(int low, int high) {
            if (low > high) return 0;
            return sumRec(root, low, high);
        }

        private int sumRec(Node node, int low, int high) {
            if (node == null) return 0;
            int sum = 0;
            if (node.val >= low && node.val <= high) sum += node.val;
            if (node.val > low) sum += sumRec(node.left, low, high);
            if (node.val < high) sum += sumRec(node.right, low, high);
            return sum;
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] vals = {50, 25, 75, 10, 30, 60, 90};
        for (int v : vals) bst.insert(v);

        System.out.println("區間 [25, 75]:");
        System.out.println("數值列表: " + bst.valuesBetween(25, 75));
        System.out.println("個數: " + bst.countBetween(25, 75));
        System.out.println("總和: " + bst.sumBetween(25, 75));

        System.out.println("\n空範圍 [35, 45]:");
        System.out.println("數值列表: " + bst.valuesBetween(35, 45));
        System.out.println("個數: " + bst.countBetween(35, 45));
        System.out.println("總和: " + bst.sumBetween(35, 45));

        System.out.println("\n違規區間 low > high [80, 20]:");
        System.out.println("數值列表: " + bst.valuesBetween(80, 20));
        System.out.println("個數: " + bst.countBetween(80, 20));
        System.out.println("總和: " + bst.sumBetween(80, 20));
    }
}