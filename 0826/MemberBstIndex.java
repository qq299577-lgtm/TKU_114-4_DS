public class MemberBstIndex {

    static class Member {
        int memberId;
        String name;
        String email;

        Member(int memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return String.format("[ID:%d 姓名:%s 信箱:%s]", memberId, name, email);
        }
    }

    static class Node {
        Member member;
        Node left, right;
        Node(Member member) { this.member = member; }
    }

    static class MemberBST {
        Node root;

        boolean insert(int id, String name, String email) {
            if (email == null || email.trim().isEmpty()) return false;
            if (find(id) != null) return false;
            root = insertRec(root, new Member(id, name, email));
            return true;
        }

        private Node insertRec(Node node, Member m) {
            if (node == null) return new Node(m);
            if (m.memberId < node.member.memberId) node.left = insertRec(node.left, m);
            else if (m.memberId > node.member.memberId) node.right = insertRec(node.right, m);
            return node;
        }

        Member find(int id) {
            Node curr = root;
            while (curr != null) {
                if (id == curr.member.memberId) return curr.member;
                curr = (id < curr.member.memberId) ? curr.left : curr.right;
            }
            return null;
        }

        boolean updateEmail(int id, String newEmail) {
            if (newEmail == null || newEmail.trim().isEmpty()) return false;
            Member m = find(id);
            if (m != null) {
                m.email = newEmail;
                return true;
            }
            return false;
        }

        boolean delete(int id) {
            if (find(id) == null) return false;
            root = deleteRec(root, id);
            return true;
        }

        private Node deleteRec(Node node, int id) {
            if (node == null) return null;
            if (id < node.member.memberId) node.left = deleteRec(node.left, id);
            else if (id > node.member.memberId) node.right = deleteRec(node.right, id);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node min = node.right;
                while (min.left != null) min = min.left;
                node.member = min.member;
                node.right = deleteRec(node.right, min.member.memberId);
            }
            return node;
        }

        void reportInOrder() {
            inOrder(root);
            System.out.println();
        }

        private void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.member + " ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        MemberBST bst = new MemberBST();
        bst.insert(103, "Alice", "alice@example.com");
        bst.insert(101, "Bob", "bob@example.com");
        bst.insert(105, "Charlie", "charlie@example.com");

        System.out.println("重複新增 101: " + bst.insert(101, "DupBob", "dupbob@example.com"));
        System.out.println("空白信箱新增 104: " + bst.insert(104, "David", "   "));

        System.out.println("尋找 103: " + bst.find(103));
        bst.updateEmail(103, "alice_new@example.com");
        System.out.println("更新信箱後 103: " + bst.find(103));

        bst.delete(101);
        System.out.print("刪除 101 後名單: ");
        bst.reportInOrder();
    }
}