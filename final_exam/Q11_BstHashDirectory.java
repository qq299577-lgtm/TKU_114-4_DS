import java.util.*;

public class Q11_BstHashDirectory {

    static class BstNode {
        int id;
        BstNode left;
        BstNode right;

        BstNode(int id) {
            this.id = id;
        }
    }

    private final Map<Integer, String> hashMap = new HashMap<>();
    private BstNode root;

    public boolean add(int id, String name) {
        if (id <= 0 || name == null) {
            return false;
        }

        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            return false;
        }

        if (hashMap.containsKey(id)) {
            return false;
        }

        hashMap.put(id, trimmedName);
        root = insertBst(root, id);
        return true;
    }

    private BstNode insertBst(BstNode node, int id) {
        if (node == null) {
            return new BstNode(id);
        }
        if (id < node.id) {
            node.left = insertBst(node.left, id);
        } else if (id > node.id) {
            node.right = insertBst(node.right, id);
        }
        return node;
    }

    public String findName(int id) {
        return hashMap.get(id);
    }

    public boolean remove(int id) {
        if (!hashMap.containsKey(id)) {
            return false;
        }

        hashMap.remove(id);
        root = removeBst(root, id);
        return true;
    }

    private BstNode removeBst(BstNode node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.id) {
            node.left = removeBst(node.left, id);
        } else if (id > node.id) {
            node.right = removeBst(node.right, id);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            BstNode minNode = getMin(node.right);
            node.id = minNode.id;
            node.right = removeBst(node.right, minNode.id);
        }
        return node;
    }

    private BstNode getMin(BstNode node) {
        BstNode curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public List<Integer> idsBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low > high) {
            return result;
        }
        rangeBst(root, low, high, result);
        return result;
    }

    private void rangeBst(BstNode node, int low, int high, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.id > low) {
            rangeBst(node.left, low, high, result);
        }

        if (node.id >= low && node.id <= high) {
            result.add(node.id);
        }

        if (node.id < high) {
            rangeBst(node.right, low, high, result);
        }
    }

    public int size() {
        return hashMap.size();
    }

    public static void main(String[] args) {
        Q11_BstHashDirectory dir = new Q11_BstHashDirectory();

        System.out.println(dir.add(50, " Alice "));
        System.out.println(dir.add(30, "Bob"));
        System.out.println(dir.add(70, "Charlie"));
        System.out.println(dir.add(20, "David"));
        System.out.println(dir.add(40, "Eva"));

        System.out.println(dir.add(30, "Duplicate"));
        System.out.println(dir.add(0, "ZeroId"));
        System.out.println(dir.add(-5, "NegativeId"));
        System.out.println(dir.add(60, null));
        System.out.println(dir.add(60, "   "));

        System.out.println(dir.findName(50));
        System.out.println(dir.findName(999));
        System.out.println(dir.size());

        System.out.println(dir.idsBetween(25, 60));
        System.out.println(dir.idsBetween(60, 25));

        System.out.println(dir.remove(30));
        System.out.println(dir.remove(30));
        System.out.println(dir.findName(30));
        System.out.println(dir.size());
        System.out.println(dir.idsBetween(20, 70));
    }
}