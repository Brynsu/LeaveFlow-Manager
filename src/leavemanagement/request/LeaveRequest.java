package leavemanagement.request;

public class LeaveRequest {

    // Private Fields [ENCAPSULATION]
    private int requestId;
    private int employeeId;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String reason;
    private String status; // Submitted, Approved, Rejected

    // Constructor
    public LeaveRequest(int requestId, int employeeId, String leaveType, String startDate, String endDate, String reason) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = "Pending"; // Default status
    }

    // Getters
    public int getRequestId() {
        return requestId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Helper Method to View Request Details
    public void viewRequest() {
        System.out.println("Request ID: " + requestId);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Leave Type: " + leaveType);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Reason: " + reason);
        System.out.println("Status: " + status);
    }
}