public class OrderManagementBst {

    enum Status { PLACED, PAID, SHIPPED, CANCELED }

    static class Order {
        int orderId;
        String customer;
        double amount;
        Status status;

        Order(int orderId, String customer, double amount) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
            this.status = Status.PLACED;
        }

        @Override
        public String toString() {
            return String.format("[訂單#%d 客戶:%s 金額:%.1f 狀態:%s]", orderId, customer, amount, status);
        }
    }

    static class Node {
        Order order;
        Node left, right;
        Node(Order o) { this.order = o; }
    }

    static class OrderBST {
        Node root;

        boolean addOrder(int orderId, String customer, double amount) {
            if (amount < 0) return false;
            if (find(orderId) != null) return false;
            root = insertRec(root, new Order(orderId, customer, amount));
            return true;
        }

        private Node insertRec(Node node, Order o) {
            if (node == null) return new Node(o);
            if (o.orderId < node.order.orderId) node.left = insertRec(node.left, o);
            else if (o.orderId > node.order.orderId) node.right = insertRec(node.right, o);
            return node;
        }

        Order find(int orderId) {
            Node curr = root;
            while (curr != null) {
                if (orderId == curr.order.orderId) return curr.order;
                curr = (orderId < curr.order.orderId) ? curr.left : curr.right;
            }
            return null;
        }

        boolean updateStatus(int orderId, Status newStatus) {
            Order o = find(orderId);
            if (o != null) {
                o.status = newStatus;
                return true;
            }
            return false;
        }

        boolean cancel(int orderId) {
            return updateStatus(orderId, Status.CANCELED);
        }

        boolean remove(int orderId) {
            Order o = find(orderId);
            if (o == null || o.status != Status.CANCELED) return false;
            root = deleteRec(root, orderId);
            return true;
        }

        private Node deleteRec(Node node, int orderId) {
            if (node == null) return null;
            if (orderId < node.order.orderId) node.left = deleteRec(node.left, orderId);
            else if (orderId > node.order.orderId) node.right = deleteRec(node.right, orderId);
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
            System.out.print("訂單區間 [" + minId + " ~ " + maxId + "]: ");
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

        double totalAmount() {
            return sumRec(root);
        }

        private double sumRec(Node node) {
            if (node == null) return 0;
            return node.order.amount + sumRec(node.left) + sumRec(node.right);
        }
    }

    public static void main(String[] args) {
        OrderBST bst = new OrderBST();
        bst.addOrder(1001, "Alice", 250.0);
        bst.addOrder(1002, "Bob", 120.0);
        bst.addOrder(1003, "Charlie", 400.0);

        System.out.println("新增負金額訂單 (-50): " + bst.addOrder(1004, "David", -50.0));

        System.out.println("刪除未取消訂單 1001: " + bst.remove(1001));

        bst.cancel(1001);
        System.out.println("取消後刪除訂單 1001: " + bst.remove(1001));

        bst.reportRange(1000, 1005);
        System.out.println("剩餘訂單總金額: " + bst.totalAmount());
    }
}