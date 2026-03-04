package leavemanagement.employee;

public class Employee {


    // Protected Fields [ ENCAPSULATION para di ma tandog ]

        protected int id;
        protected String name;
        protected String department;
        protected String email;
        protected int vacationBal;
        protected int sickBal;
        protected int emergencyBal;

        public Employee(int id, String name, String department, String email) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.email = email;
            this.vacationBal = 15;
            this.sickBal = 10;
            this.emergencyBal = 5;
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

    public void setVacationBal(int v){
        vacationBal = v;
    }

    public void setSickBal(int s){
        sickBal = s;
    }

    public void setEmergencyBal(int e){
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
}