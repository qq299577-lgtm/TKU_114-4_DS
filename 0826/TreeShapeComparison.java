public class TreeShapeComparison {

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

        int searchComparisons(int target) {
            int count = 0;
            Node curr = root;
            while (curr != null) {
                count++;
                if (target == curr.val) return count;
                curr = (target < curr.val) ? curr.left : curr.right;
            }
            return count;
        }

        int totalComparisons(int[] keys) {
            int sum = 0;
            for (int k : keys) {
                sum += searchComparisons(k);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int[] asc = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] desc = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] balanced = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        BST treeAsc = new BST();
        BST treeDesc = new BST();
        BST treeBalanced = new BST();

        for (int v : asc) treeAsc.insert(v);
        for (int v : desc) treeDesc.insert(v);
        for (int v : balanced) treeBalanced.insert(v);

        int missingKey = 16;

        System.out.printf("%-12s %-8s %-16s %-16s\n", "構建順序", "樹高", "全部鍵搜尋比較總數", "缺失鍵(16)比較次數");
        System.out.printf("%-12s %-8d %-16d %-16d\n", "升冪順序", treeAsc.height(), treeAsc.totalComparisons(asc), treeAsc.searchComparisons(missingKey));
        System.out.printf("%-12s %-8d %-16d %-16d\n", "降冪順序", treeDesc.height(), treeDesc.totalComparisons(asc), treeDesc.searchComparisons(missingKey));
        System.out.printf("%-12s %-8d %-16d %-16d\n", "接近平衡", treeBalanced.height(), treeBalanced.totalComparisons(asc), treeBalanced.searchComparisons(missingKey));
    }
}