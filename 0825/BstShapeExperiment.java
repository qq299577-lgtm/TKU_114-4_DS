public class BstShapeExperiment {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static class BST {
        Node root;

        void insert(int v) { root = insertRec(root, v); }
        private Node insertRec(Node n, int v) {
            if (n == null) return new Node(v);
            if (v < n.val) n.left = insertRec(n.left, v);
            else if (v > n.val) n.right = insertRec(n.right, v);
            return n;
        }

        int height() { return getHeight(root); }
        private int getHeight(Node n) {
            if (n == null) return 0;
            return 1 + Math.max(getHeight(n.left), getHeight(n.right));
        }

        int totalComparisons(int[] dataset) {
            int total = 0;
            for (int val : dataset) {
                Node curr = root;
                while (curr != null) {
                    total++;
                    if (val == curr.val) break;
                    curr = (val < curr.val) ? curr.left : curr.right;
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] balanced = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        int[] randomLike = {7, 3, 11, 1, 5, 9, 13, 2, 4, 6, 8, 10, 12, 14, 15};

        BST tree1 = new BST();
        BST tree2 = new BST();
        BST tree3 = new BST();

        for (int v : sorted) tree1.insert(v);
        for (int v : balanced) tree2.insert(v);
        for (int v : randomLike) tree3.insert(v);

        System.out.printf("%-15s %-8s %-12s\n", "插入順序", "樹高", "15筆全搜尋總次數");
        System.out.printf("%-15s %-8d %-12d\n", "完全升序 (傾斜)", tree1.height(), tree1.totalComparisons(sorted));
        System.out.printf("%-15s %-8d %-12d\n", "完全平衡順序", tree2.height(), tree2.totalComparisons(sorted));
        System.out.printf("%-15s %-8d %-12d\n", "半隨機順序", tree3.height(), tree3.totalComparisons(sorted));
    }
}