public class RecursiveTextTools {
    public static String reverse(String str) {
        if (str == null || str.length() <= 1) return str;
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        String cleaned = cleanString(str, 0);
        return isPalindromeHelper(cleaned, 0, cleaned.length() - 1);
    }

    private static String cleanString(String str, int index) {
        if (index >= str.length()) return "";
        char ch = str.charAt(index);
        if (Character.isWhitespace(ch)) {
            return cleanString(str, index + 1);
        }
        return Character.toLowerCase(ch) + cleanString(str, index + 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    public static int countCharacter(String str, char target) {
        if (str == null || str.isEmpty()) return 0;
        int count = (str.charAt(0) == target) ? 1 : 0;
        return count + countCharacter(str.substring(1), target);
    }

    public static void main(String[] args) {
        String[] tests = {"", "A", "Level", "Race car", "Structure", "Hello World"};
        for (String test : tests) {
            System.out.println("Original: \"" + test + "\"");
            System.out.println("Reversed: \"" + reverse(test) + "\"");
            System.out.println("Is Palindrome: " + isPalindrome(test));
            System.out.println("Count of 'e': " + countCharacter(test, 'e'));
            System.out.println("--------------------");
        }
    }
}