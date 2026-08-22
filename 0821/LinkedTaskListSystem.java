public class LinkedTaskListSystem {

    static class Task {
        String id;
        String name;

        public Task(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[" + id + ": " + name + "]";
        }
    }

    static class TaskNode {
        Task task;
        TaskNode next;

        public TaskNode(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    static class TaskLinkedList {
        private TaskNode head;
        private int size = 0;

        public boolean addFirst(Task task) {
            if (findById(task.id) != null) {
                System.out.println("重複 ID: " + task.id);
                return false;
            }
            TaskNode node = new TaskNode(task);
            node.next = head;
            head = node;
            size++;
            return true;
        }

        public boolean addLast(Task task) {
            if (findById(task.id) != null) {
                System.out.println("重複 ID: " + task.id);
                return false;
            }
            TaskNode node = new TaskNode(task);
            if (head == null) {
                head = node;
            } else {
                TaskNode curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = node;
            }
            size++;
            return true;
        }

        public Task findById(String id) {
            TaskNode curr = head;
            while (curr != null) {
                if (curr.task.id.equals(id)) {
                    return curr.task;
                }
                curr = curr.next;
            }
            return null;
        }

        public boolean removeById(String id) {
            if (head == null) return false;

            if (head.task.id.equals(id)) {
                head = head.next;
                size--;
                return true;
            }

            TaskNode prev = head;
            while (prev.next != null && !prev.next.task.id.equals(id)) {
                prev = prev.next;
            }

            if (prev.next != null) {
                prev.next = prev.next.next;
                size--;
                return true;
            }
            return false;
        }

        public boolean insertAfter(String existingId, Task task) {
            if (findById(task.id) != null) {
                System.out.println("重複 ID: " + task.id);
                return false;
            }
            TaskNode curr = head;
            while (curr != null) {
                if (curr.task.id.equals(existingId)) {
                    TaskNode newNode = new TaskNode(task);
                    newNode.next = curr.next;
                    curr.next = newNode;
                    size++;
                    return true;
                }
                curr = curr.next;
            }
            return false;
        }

        public int size() { return size; }

        public void printAll() {
            TaskNode curr = head;
            System.out.print("TaskList (size=" + size + "): ");
            while (curr != null) {
                System.out.print(curr.task + " -> ");
                curr = curr.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        System.out.println("測試刪除空 List: " + list.removeById("T1"));
        list.printAll();

        list.addLast(new Task("T1", "設計"));
        list.addLast(new Task("T2", "開發"));
        list.addLast(new Task("T3", "測試"));
        list.addFirst(new Task("T0", "需求分析"));
        list.printAll();

        list.addLast(new Task("T2", "重複測試"));

        list.insertAfter("T2", new Task("T2.5", "代碼審查"));
        list.printAll();

        System.out.println("插入不存在 ID: " + list.insertAfter("TX", new Task("T99", "無效")));
        System.out.println("刪除不存在 ID: " + list.removeById("TX"));

        list.removeById("T0");
        System.out.println("刪除 Head 後:");
        list.printAll();

        list.removeById("T2");
        System.out.println("刪除 Middle 後:");
        list.printAll();

        list.removeById("T3");
        System.out.println("刪除 Tail 後:");
        list.printAll();
    }
}