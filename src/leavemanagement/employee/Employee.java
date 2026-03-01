public class Employee {

    // Private Fields [ ENCAPSULATION ]

    private int id, vacationBal, sickBal, emergencyBal;
    private String name, department, email, password;

    // CONSTRUCTOR

    public Employee(int id, String name, String department, String email, String password, int vacationBal, int sickBal, int emergencyBal){
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.password = password;
        this.vacationBal = vacationBal;
        this.sickBal = sickBal;
        this.emergencyBal = emergencyBal;
    }

    // GETTERS

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

    public String getPassword() {
        return password;
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

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setVacationBal(int vacationBal){
        this.vacationBal = vacationBal;
    }

    public void setSickBal(int sickBal){
        this.sickBal = sickBal;
    }

    public void setEmergencyBal(int emergencyBal){
        this.emergencyBal = emergencyBal;
    }

    // METHOD TO VIEW BALANCE ( VACATION AND SICK BALANCE )

    public void viewBalances(){
        System.out.println("Vacation Balance: " + vacationBal);
        System.out.println("Sick Balance: " + sickBal);
        System.out.println("Emergency Balance: " + emergencyBal);
    }

}