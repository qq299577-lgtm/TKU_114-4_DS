public class MenuTreeSearch {
    static class MenuItem {
        String name;
        MenuItem left, right;
        MenuItem(String name) { this.name = name; }
    }

    public static boolean contains(MenuItem root, String target) {
        if (root == null || target == null) return false;
        if (root.name.equals(target)) return true;
        return contains(root.left, target) || contains(root.right, target);
    }

    public static int findDepth(MenuItem root, String target) {
        return findDepthHelper(root, target, 0);
    }

    private static int findDepthHelper(MenuItem root, String target, int depth) {
        if (root == null || target == null) return -1;
        if (root.name.equals(target)) return depth;
        int leftDepth = findDepthHelper(root.left, target, depth + 1);
        if (leftDepth != -1) return leftDepth;
        return findDepthHelper(root.right, target, depth + 1);
    }

    public static int countLeaves(MenuItem root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public static void preOrderDisplay(MenuItem root) {
        if (root == null) return;
        System.out.println(root.name);
        preOrderDisplay(root.left);
        preOrderDisplay(root.right);
    }

    public static void main(String[] args) {
        MenuItem root = new MenuItem("File");
        root.left = new MenuItem("New");
        root.left.left = new MenuItem("Project");
        root.left.right = new MenuItem("FileItem");
        root.right = new MenuItem("Save");
        root.right.right = new MenuItem("Save As");

        System.out.println("Pre-order display:");
        preOrderDisplay(root);
        System.out.println("--------------------");

        System.out.println("Contains 'Save As': " + contains(root, "Save As"));
        System.out.println("Contains 'Print': " + contains(root, "Print"));
        System.out.println("Depth of 'File': " + findDepth(root, "File"));
        System.out.println("Depth of 'Project': " + findDepth(root, "Project"));
        System.out.println("Depth of 'Print': " + findDepth(root, "Print"));
        System.out.println("Leaves Count: " + countLeaves(root));
    }
}