import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private String studentId;
    private String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return String.format("Enrollment[學號: %s, 課程: %s]", studentId, courseCode);
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollmentSet = new HashSet<>();

        Enrollment e1 = new Enrollment("S001", "CS101");
        Enrollment e2 = new Enrollment("S001", "CS102");
        Enrollment e3 = new Enrollment("S002", "CS101");
        Enrollment e4 = new Enrollment("S001", "CS101");

        System.out.println("新增 e1 (S001, CS101): " + enrollmentSet.add(e1));
        System.out.println("新增 e2 (S001, CS102 - 同人不同課): " + enrollmentSet.add(e2));
        System.out.println("新增 e3 (S002, CS101): " + enrollmentSet.add(e3));
        System.out.println("重複新增 e4 (S001, CS101 - 同人同課): " + enrollmentSet.add(e4));

        Enrollment checkObj = new Enrollment("S001", "CS101");
        System.out.println("\n使用新物件檢查 contains(S001, CS101): " + enrollmentSet.contains(checkObj));
        System.out.println("使用新物件執行 remove(S001, CS101): " + enrollmentSet.remove(checkObj));
        System.out.println("移除後再次檢查 contains(S001, CS101): " + enrollmentSet.contains(checkObj));
    }
}