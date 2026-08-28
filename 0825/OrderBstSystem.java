public class OrderBstSystem {
    static class Order {
        int orderId;
        String customer;
        int amount;

        Order(int orderId, String customer, int amount) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
        }

        @Override
        public String toString() { return String.format("[訂單#%d 客戶:%s 數量:%d]", orderId, customer, amount); }
    }

    static class Node {
        Order order;
        Node left, right;
        Node(Order o) { this.order = o; }
    }

    static class BST {
        Node root;

        void addOrder(int id, String customer, int amount) {
            root = insertRec(root, new Order(id, customer, amount));
        }

        private Node insertRec(Node node, Order o) {
            if (node == null) return new Node(o);
            if (o.orderId < node.order.orderId) node.left = insertRec(node.left, o);
            else if (o.orderId > node.order.orderId) node.right = insertRec(node.right, o);
            return node;
        }

        Order find(int id) {
            Node curr = root;
            while (curr != null) {
                if (id == curr.order.orderId) return curr.order;
                curr = (id < curr.order.orderId) ? curr.left : curr.right;
            }
            return null;
        }

        boolean updateAmount(int id, int newAmount) {
            Order o = find(id);
            if (o != null) { o.amount = newAmount; return true; }
            return false;
        }

        void cancelOrder(int id) { root = deleteRec(root, id); }
        private Node deleteRec(Node node, int id) {
            if (node == null) return null;
            if (id < node.order.orderId) node.left = deleteRec(node.left, id);
            else if (id > node.order.orderId) node.right = deleteRec(node.right, id);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node min = node.right;
                while (min.left != null) min = min.left;
                node.order = min.order;
                node.right = deleteRec(node.right, min.order.orderId);
            }
            return node;
        }

        void reportRange(int minId, int maxId) {
            System.out.print("區間報表 [" + minId + " ~ " + maxId + "]: ");
            rangeRec(root, minId, maxId);
            System.out.println();
        }

        private void rangeRec(Node node, int minId, int maxId) {
            if (node == null) return;
            if (node.order.orderId > minId) rangeRec(node.left, minId, maxId);
            if (node.order.orderId >= minId && node.order.orderId <= maxId) {
                System.out.print(node.order + " ");
            }
            if (node.order.orderId < maxId) rangeRec(node.right, minId, maxId);
        }

        void summary() {
            int[] stats = new int[2]; // [0] = count, [1] = totalAmount
            calcSummary(root, stats);
            System.out.println("訂單總結: 共 " + stats[0] + " 筆訂單，總數量為 " + stats[1]);
        }

        private void calcSummary(Node node, int[] stats) {
            if (node != null) {
                calcSummary(node.left, stats);
                stats[0]++;
                stats[1] += node.order.amount;
                calcSummary(node.right, stats);
            }
        }
    }

    public static void main(String[] args) {
        BST system = new BST();
        system.addOrder(1002, "Alice", 5);
        system.addOrder(1001, "Bob", 3);
        system.addOrder(1005, "Charlie", 10);
        system.addOrder(1003, "David", 2);

        system.updateAmount(1001, 7);
        system.cancelOrder(1005);

        system.reportRange(1001, 1003);
        system.summary();
    }
}