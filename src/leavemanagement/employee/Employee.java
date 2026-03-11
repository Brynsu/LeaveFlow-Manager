package leavemanagement.employee;

import java.time.LocalDate;

public class Employee {


    // Protected Fields

        protected int id;
        protected String name;
        protected String department;
        protected String email;
        protected int vacationBal;
        protected int sickBal;
        protected int emergencyBal;
        private int balanceYear;


        public Employee(int id, String name, String department, String email) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.email = email;
            this.vacationBal = 15;
            this.sickBal = 10;
            this.emergencyBal = 5;
            this.balanceYear = LocalDate.now().getYear();
        }

    // GETTERS diria

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public int getVacationBal() {
        return vacationBal;
    }

    public int getSickBal() {
        return sickBal;
    }

    public int getEmergencyBal() {
        return emergencyBal;
    }

    // SETTERS diria

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email){
        this.email = email;
    }

    //Added validation: Leave balances must not be negative

    public void setVacationBal(int v) {
        if (v < 0) {
            System.out.println("Vacation Leave balance cannot be negative.");
            return;
        }
        vacationBal = v;
    }

    public void setSickBal(int s) {
        if (s < 0) {
            System.out.println("Sick Leave balance cannot be negative.");
            return;
        }
        sickBal = s;
    }

    public void setEmergencyBal(int e) {
        if (e < 0) {
            System.out.println("Emergency Leave balance cannot be negative.");
            return;
        }
        emergencyBal = e;
    }

    public int getTotalLeaveBalance() {
        return vacationBal + sickBal + emergencyBal;
    }

    // METHOD TO VIEW BALANCE ( VACATION AND SICK BALANCE )

    public void viewBalances() {
        System.out.println("Vacation Balance: " + vacationBal);
        System.out.println("Sick Balance: " + sickBal);
        System.out.println("Emergency Balance: " + emergencyBal);
        System.out.println("TOTAL Leave Balance (Year): " + getTotalLeaveBalance());
    }

    // added:Resets leave balances at the start of a new calendar year.

    public void resetBalancesIfNewYear() {
        int currentYear = LocalDate.now().getYear();

        if (currentYear > balanceYear) {
            vacationBal = 15;
            sickBal = 10;
            emergencyBal = 5;
            balanceYear = currentYear;

            System.out.println("Leave balances have been reset for the new year.");
        }
    }
}
