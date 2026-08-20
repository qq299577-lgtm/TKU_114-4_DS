class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor; // 物件組合

    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    public String summary() {
        String instName = (instructor != null) ? instructor.getName() : "無指派講師";
        String instId = (instructor != null) ? instructor.getId() : "N/A";
        return String.format("課程代碼: %s | 課程名稱: %s | 授課教師: %s (ID: %s)", courseCode, title, instName, instId);
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        // 建立單一講師，供多門課程共用
        Instructor teacher = new Instructor("INS101", "張教授");

        Course c1 = new Course("CS101", "資料結構", teacher);
        Course c2 = new Course("CS102", "物件導向程式設計", teacher);

        System.out.println(c1.summary());
        System.out.println(c2.summary());
    }
}