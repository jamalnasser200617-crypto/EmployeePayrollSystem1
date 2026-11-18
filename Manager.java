/**
 * Manager - a subclass of FullTimeEmployee (multi-level inheritance).
 *
 * Inheritance chain:  Employee  ->  FullTimeEmployee  ->  Manager
 *
 * A manager earns everything a full-time employee does, plus a bonus.
 * EPF deduction and raise logic are inherited unchanged.
 */
public class Manager extends FullTimeEmployee {

    private double bonus;
    private int teamSize;

    public Manager(String name, String department,
                   double monthlySalary, double allowance,
                   double bonus, int teamSize) {
        super(name, department, monthlySalary, allowance);
        setBonus(bonus);
        setTeamSize(teamSize);
    }

    // ---------- Getters and setters ----------
    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        if (bonus < 0) {
            throw new IllegalArgumentException("Bonus cannot be negative.");
        }
        this.bonus = bonus;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        if (teamSize < 0) {
            throw new IllegalArgumentException("Team size cannot be negative.");
        }
        this.teamSize = teamSize;
    }

    // ---------- Overridden methods ----------
    @Override
    public double calculateGrossPay() {
        // Reuse the parent's calculation, then add the manager bonus
        return super.calculateGrossPay() + bonus;
    }

    @Override
    public String getEmployeeType() {
        return "Manager";
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Bonus       : RM %,.2f%n", bonus);
        System.out.printf("Team Size   : %d%n", teamSize);
    }
}
