public class TreeBugLab {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static boolean buggySearch(Node root, int target) {
        Node curr = root;
        while (curr != null) {
            if (curr.val == target) return true;
            if (target < curr.val) curr = curr.right;
            else curr = curr.left;
        }
        return false;
    }

    static boolean fixedSearch(Node root, int target) {
        Node curr = root;
        while (curr != null) {
            if (curr.val == target) return true;
            if (target < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        return false;
    }

    static void buggyInOrder(Node root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(" ");
        buggyInOrder(root.left, sb);
        buggyInOrder(root.right, sb);
    }

    static void fixedInOrder(Node root, StringBuilder sb) {
        if (root == null) return;
        fixedInOrder(root.left, sb);
        sb.append(root.val).append(" ");
        fixedInOrder(root.right, sb);
    }

    static Node buggyDeleteOneChild(Node root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = buggyDeleteOneChild(root.left, val);
        else if (val > root.val) root.right = buggyDeleteOneChild(root.right, val);
        else {
            return null;
        }
        return root;
    }

    static Node fixedDeleteOneChild(Node root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = fixedDeleteOneChild(root.left, val);
        else if (val > root.val) root.right = fixedDeleteOneChild(root.right, val);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
        }
        return root;
    }

    static boolean buggyValidate(Node root) {
        if (root == null) return true;
        if (root.left != null && root.left.val >= root.val) return false;
        if (root.right != null && root.right.val <= root.val) return false;
        return buggyValidate(root.left) && buggyValidate(root.right);
    }

    static boolean fixedValidate(Node root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return fixedValidate(root.left, min, root.val) && fixedValidate(root.right, root.val, max);
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1: 搜尋方向錯誤 ===");
        Node tree1 = new Node(20);
        tree1.left = new Node(10);
        tree1.right = new Node(30);
        System.out.println("尋找 10 (Buggy): " + buggySearch(tree1, 10));
        System.out.println("尋找 10 (Fixed): " + fixedSearch(tree1, 10));

        System.out.println("\n=== 測試 2: In-order 順序錯誤 ===");
        StringBuilder sbBuggy = new StringBuilder();
        buggyInOrder(tree1, sbBuggy);
        System.out.println("InOrder (Buggy): " + sbBuggy.toString().trim());
        StringBuilder sbFixed = new StringBuilder();
        fixedInOrder(tree1, sbFixed);
        System.out.println("InOrder (Fixed): " + sbFixed.toString().trim());

        System.out.println("\n=== 測試 3: 刪除遺失單一子項目 ===");
        Node tree3 = new Node(20);
        tree3.left = new Node(10);
        tree3.left.left = new Node(5);
        Node deletedBuggy = buggyDeleteOneChild(tree3, 10);
        System.out.println("刪除 10 後左子樹 (Buggy): " + (deletedBuggy.left == null ? "null (遺失 5)" : deletedBuggy.left.val));
        
        Node tree3Fixed = new Node(20);
        tree3Fixed.left = new Node(10);
        tree3Fixed.left.left = new Node(5);
        Node deletedFixed = fixedDeleteOneChild(tree3Fixed, 10);
        System.out.println("刪除 10 後左子樹 (Fixed): " + deletedFixed.left.val);

        System.out.println("\n=== 測試 4: 驗證只檢查直接子項目 ===");
        Node tree4 = new Node(20);
        tree4.left = new Node(10);
        tree4.left.right = new Node(25);
        System.out.println("驗證只看直接子項 (Buggy): " + buggyValidate(tree4));
        System.out.println("驗證全局邊界 (Fixed): " + fixedValidate(tree4, Long.MIN_VALUE, Long.MAX_VALUE));
    }
}