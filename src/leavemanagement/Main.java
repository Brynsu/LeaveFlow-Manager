package leavemanagement;

import leavemanagement.employee.RegularEmployee;
import leavemanagement.employee.HRAdmin;
import leavemanagement.employee.Supervisor;
import leavemanagement.request.LeaveRequest;
import leavemanagement.service.LeaveService;
import leavemanagement.service.ApprovalService;
import leavemanagement.service.ReportService;
import leavemanagement.request.ApprovalRecord;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LeaveService leaveService = new LeaveService();
        ApprovalService approvalService = new ApprovalService();
        ReportService reportService = new ReportService();

        System.out.println("=== Leave Management System ===");

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        System.out.print("Enter Vacation Balance: ");
        int vacationBal = sc.nextInt();

        System.out.print("Enter Sick Balance: ");
        int sickBal = sc.nextInt();

        System.out.print("Enter Emergency Balance: ");
        int emergencyBal = sc.nextInt();

        RegularEmployee emp = new RegularEmployee(1, name, department, email, password, vacationBal, sickBal, emergencyBal);

        boolean exit = false;
        sc.nextLine(); // consume leftover newline

        while (!exit) {
            System.out.println("\nChoose an action:");
            System.out.println("1. View Balances");
            System.out.println("2. Create Leave Request");
            System.out.println("3. View All Requests");
            System.out.println("4. Approve/Reject Request (Supervisor)");
            System.out.println("5. HR Final Approval");
            System.out.println("6. Exit");
            System.out.print("Option: ");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (option) {
                case 1 -> emp.viewBalances();

                case 2 -> {
                    System.out.print("Enter Leave Type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    String start = sc.nextLine();
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    String end = sc.nextLine();
                    System.out.print("Enter Reason: ");
                    String reason = sc.nextLine();

                    int requestId = leaveService.getAllRequests().size() + 1001;
                    LeaveRequest req = emp.createLeaveRequest(requestId, type, start, end, reason);
                    emp.submitRequest(req);
                    leaveService.addRequest(req);
                }

                case 3 -> reportService.generateAllRequestsReport(leaveService.getAllRequests());

                case 4 -> {
                    if (leaveService.getAllRequests().isEmpty()) {
                        System.out.println("No requests to approve/reject.");
                        break;
                    }
                    System.out.print("Enter Request ID to approve/reject: ");
                    int rId = sc.nextInt();
                    sc.nextLine();
                    LeaveRequest req = leaveService.getRequestById(rId);
                    if (req == null) {
                        System.out.println("Request not found.");
                        break;
                    }

                    Supervisor sup = new Supervisor(200, "Supervisor John", "IT", "sup@email.com", "pass", 0,0,0);

                    System.out.print("Approve or Reject? (A/R): ");
                    String decision = sc.nextLine();
                    if (decision.equalsIgnoreCase("A")) {
                        sup.approveRequest(req);
                        ApprovalRecord record = approvalService.approveRequest(5000 + rId, req, sup.getId(), "Supervisor approved.");
                        record.viewApproval();
                    } else {
                        System.out.print("Enter reason for rejection: ");
                        String reason = sc.nextLine();
                        sup.rejectRequest(req, reason);
                        ApprovalRecord record = approvalService.rejectRequest(5000 + rId, req, sup.getId(), reason);
                        record.viewApproval();
                    }
                }

                case 5 -> {
                    if (leaveService.getAllRequests().isEmpty()) {
                        System.out.println("No requests to approve.");
                        break;
                    }
                    System.out.print("Enter Request ID for HR approval: ");
                    int rId = sc.nextInt();
                    sc.nextLine();
                    LeaveRequest req = leaveService.getRequestById(rId);
                    if (req == null) {
                        System.out.println("Request not found.");
                        break;
                    }

                    HRAdmin hr = new HRAdmin(300, "HR Alice", "HR", "hr@email.com", "pass", 0,0,0);
                    hr.finalApprove(req);
                }

                case 6 -> exit = true;

                default -> System.out.println("Invalid option.");
            }
        }

        System.out.println("Exiting Leave Management System. Goodbye!");
        sc.close();
    }
}