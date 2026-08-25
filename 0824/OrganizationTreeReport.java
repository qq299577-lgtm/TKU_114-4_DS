import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrganizationTreeReport {
    static class OrgNode {
        String name;
        OrgNode left, right;
        OrgNode(String name) { this.name = name; }
    }

    public static String findParent(OrgNode root, String target) {
        if (root == null || target == null || root.name.equals(target)) {
            return null;
        }
        return findParentHelper(root, target);
    }

    private static String findParentHelper(OrgNode root, String target) {
        if (root == null) return null;
        if ((root.left != null && root.left.name.equals(target)) ||
            (root.right != null && root.right.name.equals(target))) {
            return root.name;
        }
        String leftSearch = findParentHelper(root.left, target);
        if (leftSearch != null) return leftSearch;
        return findParentHelper(root.right, target);
    }

    public static int findDepth(OrgNode root, String target) {
        return findDepthHelper(root, target, 0);
    }

    private static int findDepthHelper(OrgNode root, String target, int depth) {
        if (root == null || target == null) return -1;
        if (root.name.equals(target)) return depth;
        int leftDepth = findDepthHelper(root.left, target, depth + 1);
        if (leftDepth != -1) return leftDepth;
        return findDepthHelper(root.right, target, depth + 1);
    }

    public static List<String> pathFromRoot(OrgNode root, String target) {
        List<String> path = new ArrayList<>();
        if (root == null || target == null) return path;
        findPathHelper(root, target, path);
        return path;
    }

    private static boolean findPathHelper(OrgNode root, String target, List<String> path) {
        if (root == null) return false;
        path.add(root.name);
        if (root.name.equals(target)) return true;
        if (findPathHelper(root.left, target, path) || findPathHelper(root.right, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static void printByLevel(OrgNode root) {
        if (root == null) {
            System.out.println("Empty Organization");
            return;
        }
        Queue<OrgNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Level " + level + ": ");
            for (int i = 0; i < size; i++) {
                OrgNode curr = queue.poll();
                System.out.print(curr.name + " ");
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        OrgNode ceo = new OrgNode("CEO");
        ceo.left = new OrgNode("VP_Eng");
        ceo.right = new OrgNode("VP_Sales");
        ceo.left.left = new OrgNode("Dev_Team");
        ceo.left.right = new OrgNode("QA_Team");
        ceo.right.left = new OrgNode("Domestic_Sales");

        System.out.println("Organization Hierarchy:");
        printByLevel(ceo);
        System.out.println("--------------------");

        System.out.println("Parent of Dev_Team: " + findParent(ceo, "Dev_Team"));
        System.out.println("Parent of CEO: " + findParent(ceo, "CEO"));
        System.out.println("Parent of Unknown: " + findParent(ceo, "Unknown"));

        System.out.println("Depth of QA_Team: " + findDepth(ceo, "QA_Team"));
        System.out.println("Depth of Unknown: " + findDepth(ceo, "Unknown"));

        System.out.println("Path to QA_Team: " + pathFromRoot(ceo, "QA_Team"));
        System.out.println("Path to Unknown: " + pathFromRoot(ceo, "Unknown"));
    }
}