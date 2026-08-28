public class BstDuplicateCounter {
    static class Node {
        int key;
        int count;
        Node left, right;
        Node(int key) {
            this.key = key;
            this.count = 1;
        }
    }

    static class BST {
        Node root;

        void insert(int key) {
            root = insertRec(root, key);
        }

        private Node insertRec(Node node, int key) {
            if (node == null) return new Node(key);
            if (key == node.key) {
                node.count++;
            } else if (key < node.key) {
                node.left = insertRec(node.left, key);
            } else {
                node.right = insertRec(node.right, key);
            }
            return node;
        }

        void printInOrder() {
            inOrder(root);
            System.out.println();
        }

        private void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.key + "(" + node.count + ") ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        int[] data = {10, 5, 10, 20, 5, 10, 30};
        for (int val : data) tree.insert(val);
        
        System.out.print("中序輸出 (Key(Count)): ");
        tree.printInOrder();
    }
}