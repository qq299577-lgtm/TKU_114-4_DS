public class BstSearchTrace {
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

        private Node insertRec(Node root, int val) {
            if (root == null) return new Node(val);
            if (val < root.val) root.left = insertRec(root.left, val);
            else if (val > root.val) root.right = insertRec(root.right, val);
            return root;
        }

        boolean search(int target) {
            System.out.println("--- 搜尋目標: " + target + " ---");
            Node curr = root;
            int count = 0;
            while (curr != null) {
                count++;
                if (target == curr.val) {
                    System.out.printf("比較 #%d: 目前值 = %d -> 找到目標！\n", count, curr.val);
                    return true;
                } else if (target < curr.val) {
                    System.out.printf("比較 #%d: 目前值 = %d -> 往左走\n", count, curr.val);
                    curr = curr.left;
                } else {
                    System.out.printf("比較 #%d: 目前值 = %d -> 往右走\n", count, curr.val);
                    curr = curr.right;
                }
            }
            System.out.printf("比較結束 (共 %d 次): 未找到 %d\n", count, target);
            return false;
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) tree.insert(v);

        tree.search(50); // 根節點
        tree.search(30); // 內部節點
        tree.search(20); // 葉子節點
        tree.search(65); // 缺失值
    }
}