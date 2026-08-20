import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public String getMemberId() { return memberId; }

    @Override
    public String toString() {
        return String.format("會員卡號: %s | 姓名: %s | Email: %s", memberId, name, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryMember that = (LibraryMember) o;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("M001", "王小明", "ming_old@mail.com");
        LibraryMember m2 = new LibraryMember("M001", "王小明", "ming_new@gmail.com");
        LibraryMember m3 = new LibraryMember("M002", "李小華", "hua@mail.com");

        System.out.println("成員 1: " + m1);
        System.out.println("成員 2: " + m2);

        System.out.println("\n=== 比較結果 ===");
        System.out.println("m1 == m2: " + (m1 == m2) + " (記憶體位址不同)");
        System.out.println("m1.equals(m2): " + m1.equals(m2) + " (memberId 相同視為同會員)");
        System.out.println("m1.equals(null): " + m1.equals(null) + " (安全防禦)");
        System.out.println("m1.equals(m3): " + m1.equals(m3));
    }
}