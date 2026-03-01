public class Supervisor extends Employee {

    // CONSTRUCTOR

    public Supervisor(int id, String name, String department, String email, int vacationBal, int sickBal) {

        super(id, name, department, email, vacationBal, sickBal);

    }

    // REVIEW REQUEST - Shows the status and identifications of the leave request

    public void reviewRequest(LeaveRequest req) {
        System.out.println("Reviewing request ID: " + req.getRequestId());
        System.out.println("Employee ID: " + req.getEmployeeId());
        System.out.println("Status: " + req.getStatus());
    }

    // APPROVE REQUEST

    public void approveRequest(LeaveRequest req) {
        req.setStatus("Supervisor Approved");
        System.out.println("Request " + req.getRequestId() + " approved by Supervisor.");
    }

    // REJECT REQUEST - Takes String for the reason of rejection

    public void rejectRequest(LeaveRequest req, String reason) {
        req.setStatus("Rejected");
        System.out.println("Request " + req.getRequestId() + " rejected. Reason: " +  reason);
    }
}