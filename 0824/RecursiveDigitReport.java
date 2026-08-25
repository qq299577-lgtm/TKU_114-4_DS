public class RecursiveDigitReport {
    public static int digitSum(int n) {
        if (n < 0) return digitSum(-n);
        if (n < 10) return n;
        return (n % 10) + digitSum(n / 10);
    }

    public static int digitCount(int n) {
        if (n < 0) return digitCount(-n);
        if (n < 10) return 1;
        return 1 + digitCount(n / 10);
    }

    public static int countDigit(int n, int target) {
        if (n < 0) return countDigit(-n, target);
        if (n < 10) return (n == target) ? 1 : 0;
        return ((n % 10 == target) ? 1 : 0) + countDigit(n / 10, target);
    }

    public static void main(String[] args) {
        int[] tests = {50205, 0, -731};
        for (int val : tests) {
            System.out.println("Number: " + val);
            System.out.println("Sum: " + digitSum(val));
            System.out.println("Count: " + digitCount(val));
            System.out.println("Count of digit 0: " + countDigit(val, 0));
            System.out.println("Count of digit 5: " + countDigit(val, 5));
            System.out.println("--------------------");
        }
    }
}