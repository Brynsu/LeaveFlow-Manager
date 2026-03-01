package leavemanagement.service;

import leavemanagement.request.LeaveRequest;
import leavemanagement.request.ApprovalRecord;

public class ApprovalService {

    public ApprovalRecord approveRequest(int recordId, LeaveRequest request, int approverId, String comments) {

        request.setStatus("Approved");

        ApprovalRecord record = new ApprovalRecord(
                recordId,
                request.getRequestId(),
                approverId,
                "Approved",
                comments
        );

        System.out.println("Leave Request " + request.getRequestId() + " has been APPROVED.");

        return record;
    }

    public ApprovalRecord rejectRequest(int recordId, LeaveRequest request, int approverId, String comments) {

        request.setStatus("Rejected");

        ApprovalRecord record = new ApprovalRecord(
                recordId,
                request.getRequestId(),
                approverId,
                "Rejected",
                comments
        );

        System.out.println("Leave Request " + request.getRequestId() + " has been REJECTED.");

        return record;
    }
}