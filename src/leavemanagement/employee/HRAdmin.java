public class HRAdmin extends Employee {

    // CONSTRUCTOR

    public HRAdmin(int id, String name, String department, String email, String password, int vacationBal, int sickBal, int emergencyBal) {
        super(id, name, department, email, password, vacationBal, sickBal, emergencyBal);
    }

    // ADD EMPLOYEE

    public void addEmployee(Employee emp) {
        System.out.println("Employee added: " + emp.getName());
    }

    // EDIT EMPLOYEE BALANCES

    public void editBalances(Employee emp, int vacationBal, int sickBal, int emergencyBal) {
        emp.setVacationBal(vacationBal);
        emp.setSickBal(sickBal);
        emp.setEmergencyBal(emergencyBal);

        System.out.println("Balances updated for " + emp.getName());
    }

    // FINAL APPROVE LEAVE REQUEST [ HR SIDE APPROVAL ]

    public void finalApprove(LeaveRequest req) {
        req.setStatus("HR Approved");
        System.out.println("Request " + req.getRequest() + " approved by HR.");
    }

    // VIEW REQUEST INFO

    public void viewRequest(LeaveRequest req){
        System.out.println("Request ID: " + req.getRequestId());
        System.out.println("Employee ID: " + req.getEmployeeId());
        System.out.println("Status: " + req.getStatus());
    }
}