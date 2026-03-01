package leavemanagement.request;

import java.time.LocalDate;

public class ApprovalRecord {

    // Private Fields [ENCAPSULATION]
    private int recordId;
    private int requestId;
    private int approverId;
    private String decision; // Approved / Rejected
    private String comments;
    private LocalDate dateApproved;

    // Constructor
    public ApprovalRecord(int recordId, int requestId, int approverId, String decision, String comments) {
        this.recordId = recordId;
        this.requestId = requestId;
        this.approverId = approverId;
        this.decision = decision;
        this.comments = comments;
        this.dateApproved = LocalDate.now(); // Sets the approval date automatically
    }

    // Getters
    public int getRecordId() {
        return recordId;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getApproverId() {
        return approverId;
    }

    public String getDecision() {
        return decision;
    }

    public String getComments() {
        return comments;
    }

    public LocalDate getDateApproved() {
        return dateApproved;
    }

    // Setters
    public void setDecision(String decision) {
        this.decision = decision;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDateApproved(LocalDate dateApproved) {
        this.dateApproved = dateApproved;
    }

    // Helper Method to View Approval Details
    public void viewApproval() {
        System.out.println("Approval Record ID: " + recordId);
        System.out.println("Request ID: " + requestId);
        System.out.println("Approver ID: " + approverId);
        System.out.println("Decision: " + decision);
        System.out.println("Comments: " + comments);
        System.out.println("Date Approved: " + dateApproved);
    }
}