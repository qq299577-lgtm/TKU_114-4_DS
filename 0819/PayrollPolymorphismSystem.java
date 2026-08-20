abstract class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public abstract double calculatePay();

    @Override
    public String toString() {
        return String.format("員工[%s - %s] 實領薪資: $%.2f", id, name, calculatePay());
    }
}

class SalariedEmployee extends Employee {
    private double monthlySalary;

    public SalariedEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = Math.max(monthlySalary, 0);
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);
        this.hourlyRate = Math.max(hourlyRate, 0);
        this.hoursWorked = Math.max(hoursWorked, 0);
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

class CommissionEmployee extends Employee {
    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    public CommissionEmployee(String id, String name, double baseSalary, double salesAmount, double commissionRate) {
        super(id, name);
        this.baseSalary = Math.max(baseSalary, 0);
        this.salesAmount = Math.max(salesAmount, 0);
        this.commissionRate = Math.max(commissionRate, 0);
    }

    @Override
    public double calculatePay() {
        return baseSalary + (salesAmount * commissionRate);
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new SalariedEmployee("E01", "Alice", 60000),
            new HourlyEmployee("E02", "Bob", 200, 160),
            new CommissionEmployee("E03", "Charlie", 30000, 500000, 0.08),
            new SalariedEmployee("E04", "David", 45000)
        };

        double totalPayroll = 0;
        Employee highestPaid = employees[0];

        for (Employee emp : employees) {
            System.out.println(emp);
            double pay = emp.calculatePay();
            totalPayroll += pay;
            if (pay > highestPaid.calculatePay()) {
                highestPaid = emp;
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("總薪資支出: $%.2f\n", totalPayroll);
        System.out.printf("最高薪資者: %s ($%.2f)\n", highestPaid.getName(), highestPaid.calculatePay());
    }
}