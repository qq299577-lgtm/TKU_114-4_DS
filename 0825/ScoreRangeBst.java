public class ScoreRangeBst {
    static class Entry implements Comparable<Entry> {
        int score;
        int studentId;

        Entry(int score, int studentId) {
            this.score = score;
            this.studentId = studentId;
        }

        @Override
        public int compareTo(Entry o) {
            if (this.score != o.score) return Integer.compare(this.score, o.score);
            return Integer.compare(this.studentId, o.studentId);
        }

        @Override
        public String toString() { return "[ID:" + studentId + " 得分:" + score + "]"; }
    }

    static class Node {
        Entry entry;
        Node left, right;
        Node(Entry e) { this.entry = e; }
    }

    static class BST {
        Node root;

        void insert(int score, int studentId) {
            root = insertRec(root, new Entry(score, studentId));
        }

        private Node insertRec(Node node, Entry e) {
            if (node == null) return new Node(e);
            if (e.compareTo(node.entry) < 0) node.left = insertRec(node.left, e);
            else if (e.compareTo(node.entry) > 0) node.right = insertRec(node.right, e);
            return node;
        }

        void printScoreRange(int minScore, int maxScore) {
            System.out.print("分數區間 [" + minScore + ", " + maxScore + "]: ");
            rangeRec(root, minScore, maxScore);
            System.out.println();
        }

        private void rangeRec(Node node, int minScore, int maxScore) {
            if (node == null) return;
            if (node.entry.score >= minScore) rangeRec(node.left, minScore, maxScore);
            if (node.entry.score >= minScore && node.entry.score <= maxScore) {
                System.out.print(node.entry + " ");
            }
            if (node.entry.score <= maxScore) rangeRec(node.right, minScore, maxScore);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(85, 1);
        bst.insert(90, 2);
        bst.insert(85, 3); // 同分但不同 studentId
        bst.insert(70, 4);
        bst.insert(95, 5);

        bst.printScoreRange(80, 92);
    }
}