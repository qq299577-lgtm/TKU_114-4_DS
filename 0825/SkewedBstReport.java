public class SkewedBstReport {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static class BST {
        Node root;
        int size = 0;

        void insert(int val) {
            root = insertRec(root, val);
            size++;
        }

        private Node insertRec(Node node, int val) {
            if (node == null) return new Node(val);
            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            return node;
        }

        int height() { return getHeight(root); }
        private int getHeight(Node node) {
            if (node == null) return 0;
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }

        int searchCount(int target) {
            Node curr = root;
            int count = 0;
            while (curr != null) {
                count++;
                if (target == curr.val) return count;
                else if (target < curr.val) curr = curr.left;
                else curr = curr.right;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] sortedData = {10, 20, 30, 40, 50, 60, 70};
        int[] balancedData = {40, 20, 60, 10, 30, 50, 70};

        BST skewedTree = new BST();
        for (int v : sortedData) skewedTree.insert(v);

        BST balancedTree = new BST();
        for (int v : balancedData) balancedTree.insert(v);

        System.out.printf("%-12s %-8s %-8s %-12s\n", "樹類型", "大小", "高度", "搜尋(70)比較次數");
        System.out.printf("%-12s %-8d %-8d %-12d\n", "傾斜樹", skewedTree.size, skewedTree.height(), skewedTree.searchCount(70));
        System.out.printf("%-12s %-8d %-8d %-12d\n", "平衡樹", balancedTree.size, balancedTree.height(), balancedTree.searchCount(70));
    }
}