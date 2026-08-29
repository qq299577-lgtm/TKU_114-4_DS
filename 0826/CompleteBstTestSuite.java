import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static class BST {
        Node root;
        int size = 0;

        boolean insert(int val) {
            if (contains(val)) return false;
            root = insertRec(root, val);
            size++;
            return true;
        }

        private Node insertRec(Node node, int val) {
            if (node == null) return new Node(val);
            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            return node;
        }

        boolean contains(int val) {
            Node curr = root;
            while (curr != null) {
                if (val == curr.val) return true;
                curr = (val < curr.val) ? curr.left : curr.right;
            }
            return false;
        }

        boolean delete(int val) {
            if (!contains(val)) return false;
            root = deleteRec(root, val);
            size--;
            return true;
        }

        private Node deleteRec(Node node, int val) {
            if (node == null) return null;
            if (val < node.val) node.left = deleteRec(node.left, val);
            else if (val > node.val) node.right = deleteRec(node.right, val);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node min = node.right;
                while (min.left != null) min = min.left;
                node.val = min.val;
                node.right = deleteRec(node.right, min.val);
            }
            return node;
        }

        boolean isValid() {
            return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean validate(Node node, long min, long max) {
            if (node == null) return true;
            if (node.val <= min || node.val >= max) return false;
            return validate(node.left, min, node.val) && validate(node.right, node.val, max);
        }

        List<Integer> range(int low, int high) {
            List<Integer> res = new ArrayList<>();
            collectRange(root, low, high, res);
            return res;
        }

        private void collectRange(Node node, int low, int high, List<Integer> res) {
            if (node == null) return;
            if (node.val > low) collectRange(node.left, low, high, res);
            if (node.val >= low && node.val <= high) res.add(node.val);
            if (node.val < high) collectRange(node.right, low, high, res);
        }
    }

    private static int passCount = 0;
    private static int failCount = 0;

    static void check(String description, boolean condition) {
        if (condition) {
            System.out.printf("[PASS] %s\n", description);
            passCount++;
        } else {
            System.out.printf("[FAIL] %s\n", description);
            failCount++;
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        check("Empty 1: 空樹大小為 0", bst.size == 0);
        check("Empty 2: 空樹根為 null", bst.root == null);
        check("Empty 3: 空樹搜尋回傳 false", !bst.contains(10));
        check("Empty 4: 空樹刪除回傳 false", !bst.delete(10));
        check("Invariant 1: 空樹為有效 BST", bst.isValid());

        check("Insert 1: 插入單一根節點成功", bst.insert(50));
        check("Root 1: 根節點值為 50", bst.root.val == 50);
        check("Duplicate 1: 重複插入 50 失敗", !bst.insert(50));

        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        check("Invariant 2: 多節點樹為有效 BST", bst.isValid());
        check("Size: 樹大小正確為 7", bst.size == 7);
        check("Missing 1: 搜尋不存在的鍵 99 回傳 false", !bst.contains(99));
        check("Missing 2: 刪除不存在的鍵 99 回傳 false", !bst.delete(99));

        check("Delete Leaf 1: 刪除葉節點 20 成功", bst.delete(20));
        check("Delete Leaf 2: 樹中不再包含 20", !bst.contains(20));
        check("Invariant 3: 刪除葉子後結構有效", bst.isValid());

        check("Delete One Child 1: 刪除僅有一子的節點 30 成功", bst.delete(30));
        check("Delete One Child 2: 30 的子節點 40 依然存在", bst.contains(40));
        check("Invariant 4: 刪除單子節點後結構有效", bst.isValid());

        check("Delete Two Child 1: 刪除雙子根節點 50 成功", bst.delete(50));
        check("Delete Two Child 2: 50 不再存在", !bst.contains(50));
        check("Root 2: 新的根節點為 60", bst.root.val == 60);
        check("Invariant 5: 刪除雙子節點後結構有效", bst.isValid());

        List<Integer> r = bst.range(40, 75);
        check("Range 1: 範圍查詢大小正確", r.size() == 2);
        check("Range 2: 範圍查詢內容為 [40, 70]", r.contains(40) && r.contains(70));

        System.out.printf("\n測試完成: %d PASS, %d FAIL\n", passCount, failCount);
    }
}