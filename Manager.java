public class Manager extends Employee {
    public Manager(String empId, String name, double salary) throws InvalidSalaryException {
        super(empId, name, salary);
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.20; // 20% bonus
    }
}
