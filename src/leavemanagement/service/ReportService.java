package leavemanagement.service;
import leavemanagement.Company;
import leavemanagement.request.LeaveRequest;
import java.util.List;
public class ReportService {

    private Company company;

    public ReportService(Company company){
        this.company = company;
    }

    public void generateAllRequestsReport(List<LeaveRequest> requests) {
        
        System.out.println("====== ALL LEAVE REQUESTS REPORT ======");
        
        for (LeaveRequest request : requests) {
            request.displayRequest();
        }
    }
    public void generatePendingRequestsReport(List<LeaveRequest> requests) {
        
        System.out.println("====== PENDING LEAVE REQUESTS REPORT ======");
        
        for (LeaveRequest request : requests) {
            
            if (request.getStatus().equalsIgnoreCase("Pending")) {
                request.displayRequest();
            }
        }
    }
    public void generateApprovedRequestsReport(List<LeaveRequest> requests) {
        
        System.out.println("====== APPROVED LEAVE REQUESTS REPORT ======");
        
        for (LeaveRequest request : requests) {
            
            if (request.getStatus().toLowerCase().contains("approved")) {
                request.displayRequest();
            }
        }
    }
    public void generateRejectedRequestsReport(List<LeaveRequest> requests) {
        
        System.out.println("====== REJECTED LEAVE REQUESTS REPORT ======");
        
        for (LeaveRequest request : requests) {
            if (request.getStatus().toLowerCase().contains("rejected")) {
                request.displayRequest();
            }
        }
    }
}
