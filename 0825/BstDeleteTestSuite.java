public class BstDeleteTestSuite {
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

        void delete(int v) { root = deleteRec(root, v); }
        private Node deleteRec(Node n, int v) {
            if (n == null) return null;
            if (v < n.val) n.left = deleteRec(n.left, v);
            else if (v > n.val) n.right = deleteRec(n.right, v);
            else {
                if (n.left == null) return n.right;
                if (n.right == null) return n.left;
                Node min = n.right;
                while (min.left != null) min = min.left;
                n.val = min.val;
                n.right = deleteRec(n.right, min.val);
            }
            return n;
        }

        void printInOrder() {
            inOrder(root);
            System.out.println(root == null ? "(空樹)" : "");
        }

        private void inOrder(Node n) {
            if (n != null) {
                inOrder(n.left);
                System.out.print(n.val + " ");
                inOrder(n.right);
            }
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        System.out.print("1. 空樹刪除: ");
        tree.delete(10);
        tree.printInOrder();

        System.out.print("2. 缺失值刪除: ");
        tree.insert(50);
        tree.delete(99);
        tree.printInOrder();

        System.out.print("3. 單根刪除: ");
        tree.delete(50);
        tree.printInOrder();

        System.out.print("4. 一子根刪除: ");
        tree.insert(50);
        tree.insert(30);
        tree.delete(50);
        tree.printInOrder();
        tree.delete(30);

        System.out.print("5. 二子根刪除: ");
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.delete(50);
        tree.printInOrder();

        System.out.print("6. 連續刪除到空: ");
        tree.delete(30);
        tree.delete(70);
        tree.printInOrder();
    }
}