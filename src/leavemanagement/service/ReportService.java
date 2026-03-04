package leavemanagement.service;

import leavemanagement.Company;
import leavemanagement.employee.Employee;
import leavemanagement.request.LeaveRequest;

import java.util.List;

public class ReportService {

    private Company company;

    public ReportService(Company company) {
        this.company = company;
    }

    // One consistent format for ALL report types
    private void printFullReport(LeaveRequest request) {

        Employee emp = company.findEmployeeById(request.getEmployeeId());
        request.displayRequest(emp);
    }

    public void generateAllRequestsReport(List<LeaveRequest> requests) {

        System.out.println("\n==================================================");
        System.out.println("              ALL LEAVE REQUESTS REPORT");
        System.out.println("==================================================");

        if (requests.isEmpty()) {
            System.out.println("No leave requests found.");
            return;
        }

        for (LeaveRequest r : requests) {
            printFullReport(r);
        }
    }

    public void generatePendingRequestsReport(List<LeaveRequest> requests) {

        System.out.println("\n==================================================");
        System.out.println("            PENDING LEAVE REQUESTS REPORT");
        System.out.println("==================================================");

        boolean found = false;

        for (LeaveRequest r : requests) {
            if (r.getStatus().equalsIgnoreCase("Pending")) {
                printFullReport(r);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No pending leave requests found.");
        }
    }

    public void generateApprovedRequestsReport(List<LeaveRequest> requests) {

        System.out.println("\n==================================================");
        System.out.println("           APPROVED LEAVE REQUESTS REPORT");
        System.out.println("==================================================");

        boolean found = false;

        for (LeaveRequest r : requests) {
            if (r.getStatus().equalsIgnoreCase("Approved")) {
                printFullReport(r);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No approved leave requests found.");
        }
    }

    public void generateRejectedRequestsReport(List<LeaveRequest> requests) {

        System.out.println("\n==================================================");
        System.out.println("           REJECTED LEAVE REQUESTS REPORT");
        System.out.println("==================================================");

        boolean found = false;

        for (LeaveRequest r : requests) {
            if (r.getStatus().equalsIgnoreCase("Rejected")) {
                printFullReport(r);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rejected leave requests found.");
        }
    }
}
