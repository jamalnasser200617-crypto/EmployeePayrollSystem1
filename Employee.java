/**
 * Employee - abstract superclass of the payroll system.
 *
 * OOP concepts shown here:
 *  - ABSTRACTION   : abstract class that also implements the Payable interface.
 *                    Some methods are abstract and MUST be implemented by subclasses.
 *  - ENCAPSULATION : all attributes are private; access is only through
 *                    getters and validated setters.
 *  - INHERITANCE   : FullTimeEmployee and PartTimeEmployee extend this class.
 */
public abstract class Employee implements Payable {

    // Static counter used to auto-generate unique IDs (E1001, E1002, ...)
    private static int idCounter = 1000;

    // ---------- Private attributes (encapsulation) ----------
    private final String employeeId;   // never changes after creation
    private String name;
    private String department;

    /**
     * Constructor shared by all employee types.
     *
     * @param name       employee full name (must not be blank)
     * @param department department name (must not be blank)
     */
    public Employee(String name, String department) {
        idCounter++;
        this.employeeId = "E" + idCounter;
        setName(name);               // reuse setters so validation is applied
        setDepartment(department);
    }

    // ---------- Getters ----------
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    // ---------- Setters with validation ----------
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
        this.department = department.trim();
    }

    // ---------- Abstract methods (must be implemented by subclasses) ----------

    /** Each employee type calculates its gross pay differently. */
    @Override
    public abstract double calculateGrossPay();

    /** @return a readable label such as "Full-Time" or "Part-Time" */
    public abstract String getEmployeeType();

    /**
     * Increase the employee's pay rate by a percentage.
     *
     * @param percent raise percentage, e.g. 10 means +10%
     */
    public abstract void applyRaise(double percent);

    // ---------- Concrete methods (inherited, and may be overridden) ----------

    /**
     * Deductions from gross pay. By default there are none;
     * subclasses override this when deductions apply (e.g. EPF).
     */
    public double calculateDeductions() {
        return 0.0;
    }

    /** Net pay = gross pay - deductions. Implements Payable. */
    @Override
    public double calculateNetPay() {
        return calculateGrossPay() - calculateDeductions();
    }

    /**
     * Prints the information common to every employee.
     * Subclasses override this, call super.displayInfo(),
     * and then print their own extra details.
     */
    public void displayInfo() {
        System.out.println("--------------------------------------------");
        System.out.printf("ID          : %s%n", employeeId);
        System.out.printf("Name        : %s%n", name);
        System.out.printf("Type        : %s%n", getEmployeeType());
        System.out.printf("Department  : %s%n", department);
    }

    /** Prints a simple payslip using the (polymorphic) pay methods. */
    public void displayPayslip() {
        System.out.println("=========== PAYSLIP ===========");
        System.out.printf("Employee : %s (%s)%n", name, employeeId);
        System.out.printf("Type     : %s%n", getEmployeeType());
        System.out.printf("Gross    : RM %,10.2f%n", calculateGrossPay());
        System.out.printf("Deduct.  : RM %,10.2f%n", calculateDeductions());
        System.out.printf("Net Pay  : RM %,10.2f%n", calculateNetPay());
        System.out.println("===============================");
    }
}
