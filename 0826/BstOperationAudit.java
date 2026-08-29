public class BstOperationAudit {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static class BST {
        Node root;
        int size = 0;

        boolean insert(int val) {
            int prevSize = size;
            root = insertRec(root, val);
            boolean success = (size > prevSize);
            audit("新增", val, success);
            return success;
        }

        private Node insertRec(Node node, int val) {
            if (node == null) {
                size++;
                return new Node(val);
            }
            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            return node;
        }

        boolean delete(int val) {
            int prevSize = size;
            root = deleteRec(root, val);
            boolean success = (size < prevSize);
            audit("刪除", val, success);
            return success;
        }

        private Node deleteRec(Node node, int val) {
            if (node == null) return null;
            if (val < node.val) node.left = deleteRec(node.left, val);
            else if (val > node.val) node.right = deleteRec(node.right, val);
            else {
                size--;
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                Node minNode = node.right;
                while (minNode.left != null) minNode = minNode.left;
                node.val = minNode.val;
                size++;
                node.right = deleteRec(node.right, minNode.val);
            }
            return node;
        }

        int height() {
            return getHeight(root);
        }

        private int getHeight(Node node) {
            if (node == null) return 0;
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }

        boolean isValid() {
            return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean validate(Node node, long min, long max) {
            if (node == null) return true;
            if (node.val <= min || node.val >= max) return false;
            return validate(node.left, min, node.val) && validate(node.right, node.val, max);
        }

        String getInOrder() {
            StringBuilder sb = new StringBuilder();
            buildInOrder(root, sb);
            return sb.toString().trim();
        }

        private void buildInOrder(Node node, StringBuilder sb) {
            if (node != null) {
                buildInOrder(node.left, sb);
                sb.append(node.val).append(" ");
                buildInOrder(node.right, sb);
            }
        }

        private void audit(String op, int val, boolean result) {
            System.out.printf("[%s %d] 結果: %-5s | 有序: [%s] | 大小: %d | 高度: %d | 有效: %s\n",
                    op, val, result ? "成功" : "失敗", getInOrder(), size, height(), isValid());
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        bst.insert(30);

        bst.delete(99);

        bst.delete(20);

        bst.delete(30);

        bst.delete(50);
    }
}