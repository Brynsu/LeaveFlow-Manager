package leavemanagement.service;

import leavemanagement.request.LeaveRequest;
import java.util.List;

public class ReportService {

    public void generateAllRequestsReport(List<LeaveRequest> requests) {
        System.out.println("=== ALL LEAVE REQUESTS REPORT ===");

        for (LeaveRequest request : requests) {
            request.viewRequest();
            System.out.println("----------------------------");
        }
    }

    public void generatePendingRequestsReport(List<LeaveRequest> requests) {
        System.out.println("=== PENDING LEAVE REQUESTS REPORT ===");

        for (LeaveRequest request : requests) {
            if (request.getStatus().equalsIgnoreCase("Pending")) {
                request.viewRequest();
                System.out.println("----------------------------");
            }
        }
    }

    public void generateApprovedRequestsReport(List<LeaveRequest> requests) {
        System.out.println("=== APPROVED LEAVE REQUESTS REPORT ===");

        for (LeaveRequest request : requests) {
            if (request.getStatus().equalsIgnoreCase("Approved")) {
                request.viewRequest();
                System.out.println("----------------------------");
            }
        }
    }

    public void generateRejectedRequestsReport(List<LeaveRequest> requests) {
        System.out.println("=== REJECTED LEAVE REQUESTS REPORT ===");

        for (LeaveRequest request : requests) {
            if (request.getStatus().equalsIgnoreCase("Rejected")) {
                request.viewRequest();
                System.out.println("----------------------------");
            }
        }
    }
}