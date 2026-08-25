public class ThreeTraversalPractice {
    static class Node {
        char val;
        Node left, right;
        Node(char val) { this.val = val; }
    }

    public static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        Node root = new Node('M');
        root.left = new Node('F');
        root.left.left = new Node('B');
        root.right = new Node('T');
        root.right.left = new Node('R');
        root.right.right = new Node('Z');

        System.out.print("Pre-order: ");
        preOrder(root);
        System.out.println();

        System.out.print("In-order: ");
        inOrder(root);
        System.out.println();

        System.out.print("Post-order: ");
        postOrder(root);
        System.out.println();
    }
}