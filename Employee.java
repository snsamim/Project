public abstract class Employee implements Printable {
    private String empId;
    private String name;
    private double salary;

    public Employee(String empId, String name, double salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative.");
        }
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    // Abstract method for bonus calculation
    public abstract double calculateBonus();

    // Getters (optional)
    public String getEmpId()  { return empId;  }
    public String getName()   { return name;   }
    public double getSalary() { return salary; }

    @Override
    public void printDetails() {
        System.out.printf("Employee ID: %s | Name: %s | Salary: $%.2f%n",
                empId, name, salary);
    }
}
