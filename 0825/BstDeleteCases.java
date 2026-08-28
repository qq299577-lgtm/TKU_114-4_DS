public class BstDeleteCases {
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

        void delete(int val) {
            int prevSize = size;
            root = deleteRec(root, val);
            if (size < prevSize) {
                System.out.printf("成功刪除 %d -> 大小: %d, Inorder: ", val, size);
            } else {
                System.out.printf("未找到 %d -> 大小: %d, Inorder: ", val, size);
            }
            printInOrder(root);
            System.out.println();
        }

        private Node deleteRec(Node node, int val) {
            if (node == null) return null;
            if (val < node.val) node.left = deleteRec(node.left, val);
            else if (val > node.val) node.right = deleteRec(node.right, val);
            else {
                size--;
                if (node.left == null) return node.right;
                else if (node.right == null) return node.left;
                
                Node minNode = node.right;
                while (minNode.left != null) minNode = minNode.left;
                node.val = minNode.val;
                size++; // 修正遞迴刪除的重複扣除
                node.right = deleteRec(node.right, minNode.val);
            }
            return node;
        }

        private void printInOrder(Node node) {
            if (node != null) {
                printInOrder(node.left);
                System.out.print(node.val + " ");
                printInOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        // 建立測試樹: 葉子(20), 單子(30只有右子40), 雙子(50)
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) tree.insert(v);

        System.out.println("--- 測試刪除葉子節點 (20) ---");
        tree.delete(20);

        System.out.println("--- 測試刪除單子節點 (30, 現在只有右子 40) ---");
        tree.delete(30);

        System.out.println("--- 測試刪除雙子節點 (50) ---");
        tree.delete(50);
    }
}