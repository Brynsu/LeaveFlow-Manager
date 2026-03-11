package leavemanagement.service;

import leavemanagement.Company;
import leavemanagement.employee.Employee;
import leavemanagement.request.LeaveRequest;

public class ApprovalService {

    private LeaveService leaveService;
    private Company company;
    private BalanceService balanceService;

    public ApprovalService(LeaveService leaveService, Company company, BalanceService balanceService) {
        this.leaveService = leaveService;
        this.company = company;
        this.balanceService = balanceService;
    }

    public boolean approve(int requestId) {

        LeaveRequest req = leaveService.getRequestById(requestId);
        if (req == null) return false;

        Employee emp = company.findEmployeeById(req.getEmployeeId());
        if (emp == null) return false;

        boolean deducted = balanceService.deductBalance(emp, req.getLeaveType().getLeaveCode(), req.getDays());

        if (!deducted) {
            System.out.println("Approval failed. Balance was not deducted.");
            return false;
        }

        req.setStatus("Approved");
        System.out.println("Request approved and balance updated.");
        return true;
    }

    public boolean reject(int requestId) {

        LeaveRequest req = leaveService.getRequestById(requestId);
        if (req == null) return false;

        req.setStatus("Rejected");
        System.out.println("Request rejected.");
        return true;
    }
}