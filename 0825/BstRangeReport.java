public class BstRangeReport {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static class BST {
        Node root;

        void insert(int val) { root = insertRec(root, val); }
        private Node insertRec(Node node, int val) {
            if (node == null) return new Node(val);
            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            return node;
        }

        int min() {
            if (root == null) throw new IllegalStateException("樹為空");
            Node curr = root;
            while (curr.left != null) curr = curr.left;
            return curr.val;
        }

        int max() {
            if (root == null) throw new IllegalStateException("樹為空");
            Node curr = root;
            while (curr.right != null) curr = curr.right;
            return curr.val;
        }

        void printRange(int low, int high) {
            if (low > high) {
                System.out.println("錯誤: low (" + low + ") 不能大於 high (" + high + ")");
                return;
            }
            System.out.print("範圍 [" + low + ", " + high + "] 的元素: ");
            rangeRec(root, low, high);
            System.out.println();
        }

        private void rangeRec(Node node, int low, int high) {
            if (node == null) return;
            if (node.val > low) rangeRec(node.left, low, high);
            if (node.val >= low && node.val <= high) System.out.print(node.val + " ");
            if (node.val < high) rangeRec(node.right, low, high);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) tree.insert(v);

        System.out.println("最小值: " + tree.min());
        System.out.println("最大值: " + tree.max());
        tree.printRange(25, 65);
        tree.printRange(80, 20); // 邊界錯誤測試
    }
}