abstract class EmployeeBase {
    private String id;
    private String name;

    public EmployeeBase(String id, String name) {
        System.out.println("-> EmployeeBase 建構函式執行");
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public abstract double calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private double monthlySalary;

    public FullTimeEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        System.out.println("-> FullTimeEmployee 建構函式執行");
        this.monthlySalary = Math.max(monthlySalary, 0);
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends EmployeeBase {
    private double hourlyRate;
    private int hours;

    public PartTimeEmployee(String id, String name, double hourlyRate, int hours) {
        super(id, name);
        System.out.println("-> PartTimeEmployee 建構函式執行");
        this.hourlyRate = Math.max(hourlyRate, 0);
        this.hours = Math.max(hours, 0);
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hours;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        System.out.println("=== 建立全職員工 ===");
        EmployeeBase ft = new FullTimeEmployee("FT01", "Alice", 50000);
        System.out.printf("全職員工 %s 薪資: $%.1f\n\n", ft.getName(), ft.calculatePay());

        System.out.println("=== 建立兼職員工 ===");
        EmployeeBase pt = new PartTimeEmployee("PT01", "Bob", 190, 80);
        System.out.printf("兼職員工 %s 薪資: $%.1f\n", pt.getName(), pt.calculatePay());
    }
}