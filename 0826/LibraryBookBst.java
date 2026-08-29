public class LibraryBookBst {

    static class Book {
        String isbn;
        String title;
        String author;
        boolean available;

        Book(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        @Override
        public String toString() {
            return String.format("[%s: %s - %s (%s)]", isbn, title, author, available ? "在庫" : "借出");
        }
    }

    static class Node {
        Book book;
        Node left, right;
        Node(Book b) { this.book = b; }
    }

    static class BookBST {
        Node root;

        boolean insert(String isbn, String title, String author) {
            if (find(isbn) != null) return false;
            root = insertRec(root, new Book(isbn, title, author));
            return true;
        }

        private Node insertRec(Node node, Book b) {
            if (node == null) return new Node(b);
            int cmp = b.isbn.compareTo(node.book.isbn);
            if (cmp < 0) node.left = insertRec(node.left, b);
            else if (cmp > 0) node.right = insertRec(node.right, b);
            return node;
        }

        Book find(String isbn) {
            Node curr = root;
            while (curr != null) {
                int cmp = isbn.compareTo(curr.book.isbn);
                if (cmp == 0) return curr.book;
                curr = (cmp < 0) ? curr.left : curr.right;
            }
            return null;
        }

        boolean borrowBook(String isbn) {
            Book b = find(isbn);
            if (b != null && b.available) {
                b.available = false;
                return true;
            }
            return false;
        }

        boolean returnBook(String isbn) {
            Book b = find(isbn);
            if (b != null && !b.available) {
                b.available = true;
                return true;
            }
            return false;
        }

        boolean delete(String isbn) {
            Book b = find(isbn);
            if (b == null || !b.available) return false;
            root = deleteRec(root, isbn);
            return true;
        }

        private Node deleteRec(Node node, String isbn) {
            if (node == null) return null;
            int cmp = isbn.compareTo(node.book.isbn);
            if (cmp < 0) node.left = deleteRec(node.left, isbn);
            else if (cmp > 0) node.right = deleteRec(node.right, isbn);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                Node min = node.right;
                while (min.left != null) min = min.left;
                node.book = min.book;
                node.right = deleteRec(node.right, min.book.isbn);
            }
            return node;
        }

        void printRange(String low, String high) {
            System.out.print("區間查詢 [" + low + " ~ " + high + "]: ");
            rangeRec(root, low, high);
            System.out.println();
        }

        private void rangeRec(Node node, String low, String high) {
            if (node == null) return;
            if (node.book.isbn.compareTo(low) > 0) rangeRec(node.left, low, high);
            if (node.book.isbn.compareTo(low) >= 0 && node.book.isbn.compareTo(high) <= 0) {
                System.out.print(node.book + " ");
            }
            if (node.book.isbn.compareTo(high) < 0) rangeRec(node.right, low, high);
        }

        void reportInOrder() {
            System.out.print("館藏清單: ");
            inOrder(root);
            System.out.println();
        }

        private void inOrder(Node node) {
            if (node != null) {
                inOrder(node.left);
                System.out.print(node.book + " ");
                inOrder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BookBST bst = new BookBST();
        bst.insert("978-0134685991", "Effective Java", "Joshua Bloch");
        bst.insert("978-0201633610", "Design Patterns", "Erich Gamma");
        bst.insert("978-0321356680", "Effective Java 2", "Joshua Bloch");

        bst.borrowBook("978-0201633610");
        System.out.println("刪除借出中的書 (應該失敗): " + bst.delete("978-0201633610"));

        bst.returnBook("978-0201633610");
        System.out.println("歸還後再刪除: " + bst.delete("978-0201633610"));

        bst.printRange("978-0100000000", "978-0250000000");
        bst.reportInOrder();
    }
}