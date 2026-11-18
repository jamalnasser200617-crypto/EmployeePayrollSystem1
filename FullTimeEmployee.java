/**
 * FullTimeEmployee - a subclass of Employee.
 *
 * Paid a fixed monthly salary plus allowance.
 * A statutory EPF contribution (11% of basic salary) is deducted.
 *
 * OOP concepts shown here:
 *  - INHERITANCE  : uses the 'extends' keyword
 *  - POLYMORPHISM : overrides calculateGrossPay(), calculateDeductions(),
 *                   applyRaise() and displayInfo()
 */
public class FullTimeEmployee extends Employee {

    // Employee share of EPF contribution (11% of basic salary)
    private static final double EPF_RATE = 0.11;

    private double monthlySalary;
    private double allowance;

    public FullTimeEmployee(String name, String department,
                            double monthlySalary, double allowance) {
        super(name, department);          // call the Employee constructor
        setMonthlySalary(monthlySalary);
        setAllowance(allowance);
    }

    // ---------- Getters and setters ----------
    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        if (monthlySalary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.monthlySalary = monthlySalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        if (allowance < 0) {
            throw new IllegalArgumentException("Allowance cannot be negative.");
        }
        this.allowance = allowance;
    }

    // ---------- Overridden methods ----------
    @Override
    public double calculateGrossPay() {
        return monthlySalary + allowance;
    }

    @Override
    public double calculateDeductions() {
        return monthlySalary * EPF_RATE;   // EPF is calculated on basic salary
    }

    @Override
    public String getEmployeeType() {
        return "Full-Time";
    }

    @Override
    public void applyRaise(double percent) {
        monthlySalary = monthlySalary * (1 + percent / 100.0);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();               // print the common details first
        System.out.printf("Basic Salary: RM %,.2f%n", monthlySalary);
        System.out.printf("Allowance   : RM %,.2f%n", allowance);
        System.out.printf("Net Pay     : RM %,.2f%n", calculateNetPay());
    }
}
