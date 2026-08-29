public class TraversalSelector {

    static class ExpressionNode {
        String val;
        ExpressionNode left;
        ExpressionNode right;

        ExpressionNode(String val) {
            this.val = val;
        }

        ExpressionNode(String val, ExpressionNode left, ExpressionNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }
    }

    public static void printPrefix(ExpressionNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPrefix(root.left);
        printPrefix(root.right);
    }

    public static void printInfix(ExpressionNode root) {
        if (root == null) return;
        if (!root.isLeaf()) System.out.print("(");
        printInfix(root.left);
        System.out.print(root.val);
        printInfix(root.right);
        if (!root.isLeaf()) System.out.print(")");
    }

    public static void printPostfix(ExpressionNode root) {
        if (root == null) return;
        printPostfix(root.left);
        printPostfix(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        ExpressionNode n3 = new ExpressionNode("3");
        ExpressionNode n4 = new ExpressionNode("4");
        ExpressionNode nPlus = new ExpressionNode("+", n3, n4);
        ExpressionNode n5 = new ExpressionNode("5");
        ExpressionNode root = new ExpressionNode("*", nPlus, n5);

        System.out.print("前序 (前綴): ");
        printPrefix(root);
        System.out.println();

        System.out.print("中序 (中綴): ");
        printInfix(root);
        System.out.println();

        System.out.print("後序 (後綴): ");
        printPostfix(root);
        System.out.println();
    }
}