/**
 * Payable - an interface (ABSTRACTION).
 *
 * Any class that can be paid must be able to report its gross pay
 * and its net pay. The interface only says WHAT must be done, not HOW.
 */
public interface Payable {

    /** @return total pay before any deductions (RM) */
    double calculateGrossPay();

    /** @return pay after deductions have been subtracted (RM) */
    double calculateNetPay();
}
