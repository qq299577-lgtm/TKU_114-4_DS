public class BstInvariantChecker {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        // 1. 有效樹
        Node valid = new Node(10);
        valid.left = new Node(5);
        valid.right = new Node(15);

        // 2. 違規樹 1: 左子節點大於根
        Node invalid1 = new Node(10);
        invalid1.left = new Node(15);

        // 3. 違規樹 2: 右子節點小於根
        Node invalid2 = new Node(10);
        invalid2.right = new Node(5);

        // 4. 違規樹 3: 深層違規 (左子樹的右子超出根範圍)
        Node invalid3 = new Node(10);
        invalid3.left = new Node(5);
        invalid3.left.right = new Node(12);

        System.out.println("樹 1 (有效): " + isValidBST(valid));
        System.out.println("樹 2 (違規: 左子過大): " + isValidBST(invalid1));
        System.out.println("樹 3 (違規: 右子過小): " + isValidBST(invalid2));
        System.out.println("樹 4 (違規: 深層範圍錯誤): " + isValidBST(invalid3));
    }
}