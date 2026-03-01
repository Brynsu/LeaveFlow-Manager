public class Employee {

    // Private Fields [ ENCAPSULATION ]

    private int id, vacationBal, sickBal;
    private String name, department, email;

    // CONSTRUCTOR

    public Employee(int id, String name, String department, String email, int vacationBal, int sickBal){
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.vacationBal = vacationBal;
        this.sickBal = sickBal;
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

    public int getVacationBal() {
        return vacationBal;
    }

    public String getSickBal() {
        return sickBal;
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

    public void setVacationBal(int vacationBal){
        this.vacationBal = vacationBal;
    }

    public void set(int sickBal){
        this.sickBal = sickBal;
    }

    // METHOD TO VIEW BALANCE ( VACATION AND SICK BALANCE )

    public void viewBalances(){
        System.out.println("Vacation Balance: " + vacationBal);
        System.out.println("Sick Balance: " + sickBal);
    }

}