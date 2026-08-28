public class ProductInventoryBst {
    static class Product {
        int id;
        String name;
        int stock;

        Product(int id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = stock;
        }
        @Override
        public String toString() { return String.format("[ID:%d %s 庫存:%d]", id, name, stock); }
    }

    static class Node {
        Product product;
        Node left, right;
        Node(Product p) { this.product = p; }
    }

    static class BST {
        Node root;

        void addProduct(int id, String name, int stock) {
            root = insertRec(root, new Product(id, name, stock));
        }

        private Node insertRec(Node node, Product p) {
            if (node == null) return new Node(p);
            if (p.id < node.product.id) node.left = insertRec(node.left, p);
            else if (p.id > node.product.id) node.right = insertRec(node.right, p);
            return node;
        }

        Product find(int id) {
            Node curr = root;
            while (curr != null) {
                if (id == curr.product.id) return curr.product;
                curr = (id < curr.product.id) ? curr.left : curr.right;
            }
            return null;
        }

        boolean restock(int id, int qty) {
            Product p = find(id);
            if (p != null && qty > 0) { p.stock += qty; return true; }
            return false;
        }

        boolean deductStock(int id, int qty) {
            Product p = find(id);
            if (p != null && qty > 0 && p.stock >= qty) { p.stock -= qty; return true; }
            return false;
        }

        void delete(int id) { root = deleteRec(root, id); }
        private Node deleteRec(Node node, int id) {
            if (node == null) return null;
            if (id < node.product.id) node.left = deleteRec(node.left, id);
            else if (id > node.product.id) node.right = deleteRec(node.right, id);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node min = node.right;
                while (min.left != null) min = min.left;
                node.product = min.product;
                node.right = deleteRec(node.right, min.product.id);
            }
            return node;
        }

        void report() {
            inOrder(root);
            System.out.println();
        }

        private void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.product + " ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.addProduct(103, "鍵盤", 15);
        bst.addProduct(101, "滑鼠", 30);
        bst.addProduct(105, "螢幕", 8);

        bst.restock(101, 10);
        bst.deductStock(103, 5);
        bst.delete(105);

        System.out.print("庫存報表: ");
        bst.report();
    }
}