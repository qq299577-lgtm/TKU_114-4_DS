import java.util.*;

public class InterestSetComparison {

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> res = new HashSet<>(a);
        res.addAll(b);
        return res;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> res = new HashSet<>(a);
        res.retainAll(b);
        return res;
    }

    public static <T> Set<T> firstOnly(Set<T> a, Set<T> b) {
        Set<T> res = new HashSet<>(a);
        res.removeAll(b);
        return res;
    }

    public static <T> Set<T> secondaryOnly(Set<T> a, Set<T> b) {
        Set<T> res = new HashSet<>(b);
        res.removeAll(a);
        return res;
    }

    public static void main(String[] args) {
        Set<String> personA = new HashSet<>(Arrays.asList("閱讀", "登山", "程式設計", "攝影"));
        Set<String> personB = new HashSet<>(Arrays.asList("烹飪", "登山", "攝影", "音樂"));

        System.out.println("A 的興趣: " + personA);
        System.out.println("B 的興趣: " + personB);
        System.out.println("並集 (Union): " + union(personA, personB));
        System.out.println("交集 (Intersection): " + intersection(personA, personB));
        System.out.println("A 專有 (First-Only): " + firstOnly(personA, personB));
        System.out.println("B 專有 (Secondary-Only): " + secondaryOnly(personA, personB));
    }
}