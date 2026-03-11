package leavemanagement.service;

import leavemanagement.employee.Employee;

public class BalanceService {

    public boolean deductBalance(Employee emp, String leaveType, int days) {

        if (emp == null) {
            System.out.println("Employee not found.");
            return false;
        }

        if (days <= 0) {
            System.out.println("Invalid number of leave days.");
            return false;
        }

        if (leaveType.equalsIgnoreCase("VL")) {

            if (emp.getVacationBal() < days) {
                System.out.println("Not enough Vacation Leave balance.");
                return false;
            }

            emp.setVacationBal(emp.getVacationBal() - days);

        } else if (leaveType.equalsIgnoreCase("SL")) {

            if (emp.getSickBal() < days) {
                System.out.println("Not enough Sick Leave balance.");
                return false;
            }

            emp.setSickBal(emp.getSickBal() - days);

        } else if (leaveType.equalsIgnoreCase("EL")) {

            if (emp.getEmergencyBal() < days) {
                System.out.println("Not enough Emergency Leave balance.");
                return false;
            }

            emp.setEmergencyBal(emp.getEmergencyBal() - days);

        } else {
            System.out.println("Invalid leave type.");
            return false;
        }

        System.out.println("Balance deducted successfully.");
        return true;
    }
}