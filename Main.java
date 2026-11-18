import java.util.Scanner;

/**
 * Main - entry point of the Employee Payroll System (console application).
 *
 * Uses a Scanner for input, a while loop for the menu,
 * a switch statement for menu choices, and if/else for validation.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PayrollSystem payroll = new PayrollSystem();

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("      EMPLOYEE PAYROLL SYSTEM (Java OOP)    ");
        System.out.println("============================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ", 0, 10);
            System.out.println();

            switch (choice) {
                case 1:  addFullTime();                       break;
                case 2:  addPartTime();                       break;
                case 3:  addManager();                        break;
                case 4:  payroll.displayAllEmployees();       break;
                case 5:  showPayslip();                       break;
                case 6:  giveRaise();                         break;
                case 7:  removeEmployee();                    break;
                case 8:  payroll.displayPayrollSummary();     break;
                case 9:  payroll.demonstratePolymorphism();   break;
                case 10: loadSampleData();                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using the Payroll System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    // ------------------------------------------------------------------
    // Menu
    // ------------------------------------------------------------------
    private static void showMenu() {
        System.out.println();
        System.out.println("------------------ MENU ------------------");
        System.out.println(" 1. Add full-time employee");
        System.out.println(" 2. Add part-time employee");
        System.out.println(" 3. Add manager");
        System.out.println(" 4. Display all employees");
        System.out.println(" 5. View payslip (by ID)");
        System.out.println(" 6. Give salary / rate raise (by ID)");
        System.out.println(" 7. Remove employee (by ID)");
        System.out.println(" 8. Payroll summary");
        System.out.println(" 9. Polymorphism demo");
        System.out.println("10. Load sample data");
        System.out.println(" 0. Exit");
        System.out.println("------------------------------------------");
    }

    // ------------------------------------------------------------------
    // Menu actions
    // ------------------------------------------------------------------
    private static void addFullTime() {
        System.out.println("--- Add Full-Time Employee ---");
        String name = readText("Name: ");
        String dept = readText("Department: ");
        double salary = readDouble("Monthly basic salary (RM): ");
        double allowance = readDouble("Monthly allowance (RM): ");

        FullTimeEmployee emp = new FullTimeEmployee(name, dept, salary, allowance);
        payroll.addEmployee(emp);
        System.out.println("Added successfully. Employee ID: " + emp.getEmployeeId());
    }

    private static void addPartTime() {
        System.out.println("--- Add Part-Time Employee ---");
        String name = readText("Name: ");
        String dept = readText("Department: ");
        double rate = readDouble("Hourly rate (RM): ");
        int hours = readInt("Hours worked this month: ", 0, 744);

        PartTimeEmployee emp = new PartTimeEmployee(name, dept, rate, hours);
        payroll.addEmployee(emp);
        System.out.println("Added successfully. Employee ID: " + emp.getEmployeeId());
    }

    private static void addManager() {
        System.out.println("--- Add Manager ---");
        String name = readText("Name: ");
        String dept = readText("Department: ");
        double salary = readDouble("Monthly basic salary (RM): ");
        double allowance = readDouble("Monthly allowance (RM): ");
        double bonus = readDouble("Bonus (RM): ");
        int team = readInt("Team size: ", 0, 1000);

        Manager emp = new Manager(name, dept, salary, allowance, bonus, team);
        payroll.addEmployee(emp);
        System.out.println("Added successfully. Employee ID: " + emp.getEmployeeId());
    }

    private static void showPayslip() {
        Employee emp = askForEmployee();
        if (emp != null) {
            emp.displayPayslip();
        }
    }

    private static void giveRaise() {
        Employee emp = askForEmployee();
        if (emp == null) {
            return;
        }
        double percent = readDouble("Raise percentage (e.g. 10 for 10%): ");
        double before = emp.calculateGrossPay();
        emp.applyRaise(percent);             // polymorphic: each type raises differently
        double after = emp.calculateGrossPay();
        System.out.printf("%s's gross pay changed from RM %,.2f to RM %,.2f%n",
                emp.getName(), before, after);
    }

    private static void removeEmployee() {
        String id = readText("Enter employee ID to remove: ");
        if (payroll.removeEmployee(id)) {
            System.out.println("Employee " + id.toUpperCase() + " removed.");
        } else {
            System.out.println("No employee found with ID " + id + ".");
        }
    }

    /** Asks for an ID and returns the matching employee (or null with a message). */
    private static Employee askForEmployee() {
        if (payroll.isEmpty()) {
            System.out.println("No employees in the system yet.");
            return null;
        }
        String id = readText("Enter employee ID: ");
        Employee emp = payroll.findById(id);
        if (emp == null) {
            System.out.println("No employee found with ID " + id + ".");
        }
        return emp;
    }

    /** Adds one of each employee type so the program can be demonstrated quickly. */
    private static void loadSampleData() {
        payroll.addEmployee(new FullTimeEmployee("Aisyah Rahman", "Finance", 4500, 300));
        payroll.addEmployee(new PartTimeEmployee("Daniel Lim", "Support", 15, 80));
        payroll.addEmployee(new Manager("Kumar Raj", "IT", 8000, 800, 1500, 6));
        System.out.println("Sample data loaded: 3 employees added.");
    }

    // ------------------------------------------------------------------
    // Input helpers (loop until the user enters valid data)
    // ------------------------------------------------------------------
    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("  Input cannot be empty. Try again.");
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
                System.out.println("  Value cannot be negative. Try again.");
            } catch (NumberFormatException ex) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("  Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException ex) {
                System.out.println("  Please enter a whole number.");
            }
        }
    }
}
