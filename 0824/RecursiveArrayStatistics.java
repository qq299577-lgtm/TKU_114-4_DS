public class RecursiveArrayStatistics {
    public static int maximum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        return maximumHelper(arr, 0);
    }

    private static int maximumHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        int maxRest = maximumHelper(arr, index + 1);
        return (arr[index] > maxRest) ? arr[index] : maxRest;
    }

    public static int minimum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        return minimumHelper(arr, 0);
    }

    private static int minimumHelper(int[] arr, int index) {
        if (index == arr.length - 1) return arr[index];
        int minRest = minimumHelper(arr, index + 1);
        return (arr[index] < minRest) ? arr[index] : minRest;
    }

    public static int countAbove(int[] arr, int threshold) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        return countAboveHelper(arr, threshold, 0);
    }

    private static int countAboveHelper(int[] arr, int threshold, int index) {
        if (index == arr.length) return 0;
        int count = (arr[index] > threshold) ? 1 : 0;
        return count + countAboveHelper(arr, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {12, 45, 7, 23, 89, 5, 67};
        System.out.println("Max: " + maximum(arr));
        System.out.println("Min: " + minimum(arr));
        System.out.println("Count > 20: " + countAbove(arr, 20));
    }
}