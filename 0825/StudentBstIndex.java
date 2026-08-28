public class StudentBstIndex {
    static class Student {
        int id;
        String name;
        Student(int id, String name) {
            this.id = id;
            this.name = name;
        }
        @Override
        public String toString() { return "[" + id + ": " + name + "]"; }
    }

    static class Node {
        Student student;
        Node left, right;
        Node(Student s) { this.student = s; }
    }

    static class BST {
        Node root;

        boolean insert(Student s) {
            if (search(s.id) != null) return false;
            root = insertRec(root, s);
            return true;
        }

        private Node insertRec(Node node, Student s) {
            if (node == null) return new Node(s);
            if (s.id < node.student.id) node.left = insertRec(node.left, s);
            else if (s.id > node.student.id) node.right = insertRec(node.right, s);
            return node;
        }

        Student search(int id) {
            Node curr = root;
            while (curr != null) {
                if (id == curr.student.id) return curr.student;
                curr = (id < curr.student.id) ? curr.left : curr.right;
            }
            return null;
        }

        void delete(int id) {
            root = deleteRec(root, id);
        }

        private Node deleteRec(Node node, int id) {
            if (node == null) return null;
            if (id < node.student.id) node.left = deleteRec(node.left, id);
            else if (id > node.student.id) node.right = deleteRec(node.right, id);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node min = node.right;
                while (min.left != null) min = min.left;
                node.student = min.student;
                node.right = deleteRec(node.right, min.student.id);
            }
            return node;
        }

        void printInOrder(Node node) {
            if (node != null) {
                printInOrder(node.left);
                System.out.print(node.student + " ");
                printInOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        System.out.println("插入 101: " + bst.insert(new Student(101, "Alice")));
        System.out.println("插入 102: " + bst.insert(new Student(102, "Bob")));
        System.out.println("重複插入 101: " + bst.insert(new Student(101, "Alice Duplicate")));
        
        System.out.println("查詢 102: " + bst.search(102));
        bst.delete(101);
        System.out.print("刪除 101 後索引內容: ");
        bst.printInOrder(bst.root);
        System.out.println();
    }
}