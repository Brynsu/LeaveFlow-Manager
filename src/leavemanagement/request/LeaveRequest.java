package leavemanagement.request;

import leavemanagement.employee.Employee;

public class LeaveRequest {

    private int requestId;
    private int employeeId;
    private String leaveType;
    private String startDate;
    private String endDate;
    private int days;
    private String reason;
    private String status;

    public LeaveRequest(int requestId, int employeeId, String leaveType, String startDate, String endDate, int days, String reason) {

        this.requestId = requestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.reason = reason;
        this.status = "Pending";
    }

    public int getRequestId() { return requestId; }

    public int getEmployeeId() { return employeeId; }

    public String getLeaveType() { return leaveType; }

    public int getDays() { return days; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void displayRequest(Employee emp) {

        System.out.println("--------------------------------------------------");
        System.out.println("Request ID : " + requestId);

        if (emp != null) {
            System.out.println("Employee   : " + emp.getName());
            System.out.println("Department : " + emp.getDepartment());
            System.out.println("Email      : " + emp.getEmail());
        } else {
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Employee   : (Not found)");
            System.out.println("Department : (Not found)");
            System.out.println("Email      : (Not found)");
        }

        System.out.println("Leave Type : " + leaveType);
        System.out.println("Start Date : " + startDate);
        System.out.println("End Date   : " + endDate);
        System.out.println("Days       : " + days);
        System.out.println("Reason     : " + reason);
        System.out.println("Status     : " + status);
        System.out.println("--------------------------------------------------");
    }

    public void displayRequest() {

        System.out.println("\nRequest ID: " + requestId);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Leave Type: " + leaveType);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Days: " + days);
        System.out.println("Reason: " + reason);
        System.out.println("Status: " + status);
        System.out.println("----------------------------");
    }
}
