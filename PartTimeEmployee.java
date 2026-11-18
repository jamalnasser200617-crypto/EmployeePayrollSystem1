/**
 * PartTimeEmployee - a subclass of Employee.
 *
 * Paid by the hour: hourlyRate x hoursWorked. No EPF deduction is applied,
 * so the inherited calculateDeductions() (which returns 0) is reused as-is.
 *
 * OOP concepts shown here:
 *  - INHERITANCE  : extends Employee
 *  - POLYMORPHISM : gives its own version of the abstract methods
 */
public class PartTimeEmployee extends Employee {

    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String name, String department,
                            double hourlyRate, int hoursWorked) {
        super(name, department);
        setHourlyRate(hourlyRate);
        setHoursWorked(hoursWorked);
    }

    // ---------- Getters and setters ----------
    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }
        this.hoursWorked = hoursWorked;
    }

    // ---------- Overridden methods ----------
    @Override
    public double calculateGrossPay() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public String getEmployeeType() {
        return "Part-Time";
    }

    @Override
    public void applyRaise(double percent) {
        hourlyRate = hourlyRate * (1 + percent / 100.0);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Hourly Rate : RM %,.2f%n", hourlyRate);
        System.out.printf("Hours Worked: %d%n", hoursWorked);
        System.out.printf("Net Pay     : RM %,.2f%n", calculateNetPay());
    }
}
