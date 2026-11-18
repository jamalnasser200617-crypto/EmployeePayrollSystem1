import java.util.ArrayList;

/**
 * PayrollSystem - manages the collection of employees and the payroll operations.
 *
 * The list is declared as ArrayList<Employee> (superclass type), so it can hold
 * FullTimeEmployee, PartTimeEmployee and Manager objects together.
 * This is what makes RUNTIME POLYMORPHISM possible below.
 */
public class PayrollSystem {

    private final ArrayList<Employee> employees = new ArrayList<>();

    /** Add any kind of employee to the system. */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /** @return the employee with the given ID, or null if not found */
    public Employee findById(String id) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }

    /** Remove an employee by ID. @return true if someone was removed */
    public boolean removeEmployee(String id) {
        Employee e = findById(id);
        if (e == null) {
            return false;
        }
        employees.remove(e);
        return true;
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    /** Display every employee. displayInfo() resolves at runtime per object type. */
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system yet.");
            return;
        }
        for (Employee e : employees) {
            e.displayInfo();               // POLYMORPHIC CALL
        }
        System.out.println("--------------------------------------------");
        System.out.println("Total employees: " + employees.size());
    }

    /** Sum of net pay for all employees. */
    public double calculateTotalNetPayroll() {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateNetPay();  // POLYMORPHIC CALL
        }
        return total;
    }

    /** Sum of gross pay for all employees. */
    public double calculateTotalGrossPayroll() {
        double total = 0;
        for (Employee e : employees) {
            total += e.calculateGrossPay();
        }
        return total;
    }

    /** Print a payroll summary table. */
    public void displayPayrollSummary() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system yet.");
            return;
        }
        System.out.println("=================================================================");
        System.out.printf("%-7s %-16s %-10s %12s %12s%n",
                "ID", "Name", "Type", "Gross (RM)", "Net (RM)");
        System.out.println("-----------------------------------------------------------------");
        for (Employee e : employees) {
            System.out.printf("%-7s %-16s %-10s %,12.2f %,12.2f%n",
                    e.getEmployeeId(), e.getName(), e.getEmployeeType(),
                    e.calculateGrossPay(), e.calculateNetPay());
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-35s %,12.2f %,12.2f%n", "TOTAL",
                calculateTotalGrossPayroll(), calculateTotalNetPayroll());
        System.out.println("=================================================================");
    }

    /**
     * Demonstrates RUNTIME POLYMORPHISM.
     *
     * Every variable here has the type Employee (the superclass), but the
     * method that actually runs is decided by the real object at runtime.
     */
    public void demonstratePolymorphism() {
        if (employees.isEmpty()) {
            System.out.println("Add some employees first (or load sample data).");
            return;
        }
        System.out.println("Calling calculateGrossPay() through Employee references:");
        System.out.println();
        for (Employee e : employees) {     // 'e' is declared as Employee
            System.out.printf("%-16s is a %-10s -> RM %,10.2f  (%s version)%n",
                    e.getName(),
                    e.getEmployeeType(),
                    e.calculateGrossPay(),
                    e.getClass().getSimpleName());
        }
    }
}
