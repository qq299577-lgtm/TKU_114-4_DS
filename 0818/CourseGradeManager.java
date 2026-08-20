class CourseGrade {
    private String studentId;
    private String name;
    private double regularScore; // 平時 50%
    private double midtermScore; // 期中 20%
    private double finalScore;   // 期末 20%
    private double attendance;   // 出席 10%

    public CourseGrade(String studentId, String name, double regularScore, double midtermScore, double finalScore, double attendance) {
        this.studentId = studentId;
        this.name = name;
        this.regularScore = clamp(regularScore);
        this.midtermScore = clamp(midtermScore);
        this.finalScore = clamp(finalScore);
        this.attendance = clamp(attendance);
    }

    private double clamp(double score) {
        return Math.max(0, Math.min(100, score));
    }

    public double calculateFinalScore() {
        return (regularScore * 0.5) + (midtermScore * 0.2) + (finalScore * 0.2) + (attendance * 0.1);
    }

    public String getLevel() {
        double score = calculateFinalScore();
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("學號: %s | 姓名: %-4s | 總分: %5.1f | 等第: %s",
                studentId, name, calculateFinalScore(), getLevel());
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] list = {
            new CourseGrade("414601", "張偉", 85, 80, 90, 100),
            new CourseGrade("414602", "李華", 60, 50, 55, 70),
            new CourseGrade("414603", "王萌", 95, 92, 98, 100),
            new CourseGrade("414604", "劉強", 40, 45, 30, 60),
            new CourseGrade("414605", "趙敏", 75, 70, 80, 85)
        };

        System.out.println("=== 班級成績清單 ===");
        double totalSum = 0;
        CourseGrade topStudent = list[0];

        for (CourseGrade cg : list) {
            System.out.println(cg);
            totalSum += cg.calculateFinalScore();
            if (cg.calculateFinalScore() > topStudent.calculateFinalScore()) {
                topStudent = cg;
            }
        }

        System.out.printf("\n全班平均分數: %.2f\n", (totalSum / list.length));
        System.out.println("最高分學生: " + topStudent.getName() + " (" + topStudent.calculateFinalScore() + " 分)");

        System.out.println("\n=== 不及格名單 (總分 < 60) ===");
        for (CourseGrade cg : list) {
            if (cg.calculateFinalScore() < 60) {
                System.out.println(cg.getStudentId() + " " + cg.getName() + " - " + cg.calculateFinalScore() + " 分 (等第 " + cg.getLevel() + ")");
            }
        }
    }
}