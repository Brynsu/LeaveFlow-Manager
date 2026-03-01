package leavemanagement.employee;

import leavemanagement.request.LeaveRequest;

public class Supervisor extends Employee {

    public Supervisor(int id, String name, String department, String email, String password, int vacationBal, int sickBal, int emergencyBal) {
        super(id, name, department, email, password, vacationBal, sickBal, emergencyBal);
    }

    // APPROVE LEAVE REQUEST
    public void approveRequest(LeaveRequest req) {
        req.setStatus("Supervisor Approved");
        System.out.println("Leave Request " + req.getRequestId() + " approved by Supervisor.");
    }

    // REJECT LEAVE REQUEST
    public void rejectRequest(LeaveRequest req, String reason) {
        req.setStatus("Rejected");
        System.out.println("Leave Request " + req.getRequestId() + " rejected by Supervisor. Reason: " + reason);
    }

    // VIEW LEAVE REQUEST INFO
    public void viewRequest(LeaveRequest req) {
        System.out.println("Request ID: " + req.getRequestId());
        System.out.println("Employee ID: " + req.getEmployeeId());
        System.out.println("Leave Type: " + req.getLeaveType());
        System.out.println("Start Date: " + req.getStartDate());
        System.out.println("End Date: " + req.getEndDate());
        System.out.println("Reason: " + req.getReason());
        System.out.println("Status: " + req.getStatus());
    }
}