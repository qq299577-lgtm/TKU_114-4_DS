import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {
        if (text == null) return false;
        if (text.isEmpty()) return true;

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }
        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(Deque<String> urgentQueue) {
        return urgentQueue.pollFirst();
    }

    public static List<String> process(String[] commands) {
        if (commands == null) return Collections.emptyList();

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();
        List<String> result = new ArrayList<>();

        for (String cmd : commands) {
            if (cmd == null || cmd.isBlank()) continue;
            String trimmed = cmd.trim();

            if (trimmed.equals("PROCESS")) {
                if (!urgentQueue.isEmpty()) {
                    result.add(takeUrgentCheckpoint(urgentQueue));
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.pollFirst());
                } else {
                    result.add("EMPTY");
                }
            } else if (trimmed.startsWith("NORMAL ")) {
                String id = trimmed.substring(7).trim();
                if (!id.isEmpty()) {
                    normalQueue.offerLast(id);
                }
            } else if (trimmed.startsWith("URGENT ")) {
                String id = trimmed.substring(7).trim();
                if (!id.isEmpty()) {
                    urgentQueue.offerLast(id);
                }
            }
        }
        return result;
    }
}