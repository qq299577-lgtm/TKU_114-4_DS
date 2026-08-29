public class CourseBstIndex {

    static class Course {
        String code;
        String title;
        int credits;

        Course(String code, String title, int credits) {
            this.code = code;
            this.title = title;
            this.credits = credits;
        }

        @Override
        public String toString() {
            return String.format("[%s: %s (%d學分)]", code, title, credits);
        }
    }

    static class Node {
        Course course;
        Node left, right;
        Node(Course c) { this.course = c; }
    }

    static class CourseBST {
        Node root;

        boolean insert(String code, String title, int credits) {
            if (credits < 1 || credits > 6) return false;
            if (find(code) != null) return false;
            root = insertRec(root, new Course(code, title, credits));
            return true;
        }

        private Node insertRec(Node node, Course c) {
            if (node == null) return new Node(c);
            int cmp = c.code.compareTo(node.course.code);
            if (cmp < 0) node.left = insertRec(node.left, c);
            else if (cmp > 0) node.right = insertRec(node.right, c);
            return node;
        }

        Course find(String code) {
            Node curr = root;
            while (curr != null) {
                int cmp = code.compareTo(curr.course.code);
                if (cmp == 0) return curr.course;
                curr = (cmp < 0) ? curr.left : curr.right;
            }
            return null;
        }

        boolean updateCredits(String code, int newCredits) {
            if (newCredits < 1 || newCredits > 6) return false;
            Course c = find(code);
            if (c != null) {
                c.credits = newCredits;
                return true;
            }
            return false;
        }

        boolean delete(String code) {
            if (find(code) == null) return false;
            root = deleteRec(root, code);
            return true;
        }

        private Node deleteRec(Node node, String code) {
            if (node == null) return null;
            int cmp = code.compareTo(node.course.code);
            if (cmp < 0) node.left = deleteRec(node.left, code);
            else if (cmp > 0) node.right = deleteRec(node.right, code);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node min = node.right;
                while (min.left != null) min = min.left;
                node.course = min.course;
                node.right = deleteRec(node.right, min.course.code);
            }
            return node;
        }

        void printRange(String startCode, String endCode) {
            System.out.print("範圍查詢 [" + startCode + " ~ " + endCode + "]: ");
            rangeRec(root, startCode, endCode);
            System.out.println();
        }

        private void rangeRec(Node node, String startCode, String endCode) {
            if (node == null) return;
            if (node.course.code.compareTo(startCode) > 0) rangeRec(node.left, startCode, endCode);
            if (node.course.code.compareTo(startCode) >= 0 && node.course.code.compareTo(endCode) <= 0) {
                System.out.print(node.course + " ");
            }
            if (node.course.code.compareTo(endCode) < 0) rangeRec(node.right, startCode, endCode);
        }

        void printInOrder() {
            System.out.print("課程清單: ");
            inOrder(root);
            System.out.println();
        }

        private void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.course + " ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        CourseBST bst = new CourseBST();
        bst.insert("CS101", "計算機概論", 3);
        bst.insert("CS201", "資料結構", 4);
        bst.insert("MATH101", "微積分", 4);
        bst.insert("EE101", "電路學", 3);

        System.out.println("重複代碼加入 CS101: " + bst.insert("CS101", "重名課", 2));
        System.out.println("無效學分加入 (0學分): " + bst.insert("CS999", "專題", 0));

        bst.updateCredits("CS101", 4);
        bst.delete("EE101");

        bst.printRange("CS100", "CS300");
        bst.printInOrder();
    }
}