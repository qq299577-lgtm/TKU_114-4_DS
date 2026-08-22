import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private final Deque<String> undoStack = new ArrayDeque<>();
    private final Deque<String> redoStack = new ArrayDeque<>();
    private String text = "";

    public void type(String newText) {
        undoStack.push(text);
        redoStack.clear();
        text += newText;
        printStatus("輸入: \"" + newText + "\"");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("無可撤銷的操作");
            return;
        }
        redoStack.push(text);
        text = undoStack.pop();
        printStatus("執行 Undo");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("無可重做的操作");
            return;
        }
        undoStack.push(text);
        text = redoStack.pop();
        printStatus("執行 Redo");
    }

    private void printStatus(String action) {
        System.out.println("[" + action + "] 目前文字: \"" + text + "\" | Undo棧: " + undoStack + " | Redo棧: " + redoStack);
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        editor.type("Hello");
        editor.type(" World");
        editor.type("!");

        editor.undo();
        editor.undo();
        editor.redo();

        editor.type(" Java");
        editor.redo();
        editor.undo();
        editor.undo();
        editor.undo();
    }
}