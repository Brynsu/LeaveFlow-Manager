package leavemanagement.service;
import leavemanagement.employee.Employee;

// mo handle sa leave balance deduction after approval
public class BalanceService {

    public void deductBalance(Employee emp, String leaveType, int days) {

        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }

        if (leaveType.equalsIgnoreCase("VL")) {
            emp.setVacationBal(emp.getVacationBal() - days);

        } else if (leaveType.equalsIgnoreCase("SL")) {

            emp.setSickBal(emp.getSickBal() - days);

        } else if (leaveType.equalsIgnoreCase("EL")) {

            emp.setEmergencyBal(emp.getEmergencyBal() - days);
        } else {

            System.out.println("Invalid leave type.");
            return;
        }

        System.out.println("Balance deducted successfully.");
    }
}