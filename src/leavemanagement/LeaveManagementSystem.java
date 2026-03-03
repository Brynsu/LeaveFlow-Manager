package leavemanagement;
import java.util.Scanner;
import leavemanagement.employee.Employee;
import leavemanagement.employee.RegularEmployee;
import leavemanagement.employee.Supervisor;
import leavemanagement.employee.HRAdmin;
import leavemanagement.request.LeaveRequest;
import leavemanagement.service.LeaveService;
import leavemanagement.service.ApprovalService;
import leavemanagement.service.BalanceService;
import leavemanagement.service.ReportService;

public class LeaveManagementSystem {
    private Scanner sc;
    private boolean systemRunning;
    private Company company;
    private LeaveService leaveService;
    private BalanceService balanceService;
    private ApprovalService approvalService;
    private ReportService reportService;
    private Employee currentUser;

    public LeaveManagementSystem() {
        sc = new Scanner(System.in);
        systemRunning = true;
        company = new Company();
        leaveService = new LeaveService();
        balanceService = new BalanceService();
        approvalService = new ApprovalService(leaveService, company, balanceService);
        reportService = new ReportService(company);

    }
    public void startSystem() {
        while (systemRunning) {
            System.out.println("------------------------------------");
            System.out.println("\t\t Leave Flow Manager\t\t");
            System.out.println("------------------------------------");
            System.out.println("\t\t\t   LOGIN\t\t");
            System.out.print("Enter Employee ID (1 - Employee, 2 - Supervisor, 3 - HR Admin): ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            String idStr = String.valueOf(id);
            char firstDigit = idStr.charAt(0);

            if (firstDigit == '1' && !department.equalsIgnoreCase("HR")) {
                currentUser = company.getOrCreateEmployee(id, name, department, email, "EMPLOYEE");
                System.out.println("\nLogin Successful as Employee!");
                employeeMenu();

            } else if (firstDigit == '2' && !department.equalsIgnoreCase("HR")) {
                currentUser = company.getOrCreateEmployee(id, name, department, email, "SUPERVISOR");
                System.out.println("\nLogin Successful as Supervisor!");
                supervisorMenu();

            } else if (firstDigit == '3' && department.equalsIgnoreCase("HR")) {
                currentUser = company.getOrCreateEmployee(id, name, department, email, "HR");
                System.out.println("\nLogin Successful as HR Admin!");
                hrAdminMenu();

            } else {
                currentUser = null;
                System.out.println("\nInvalid credentials based on company rules.");
            }

            System.out.println("\nDo you want to login again? (yes/no)");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("no")) {
                systemRunning = false;
                System.out.println("Exiting system...");
            }
        }
    }


    // EMPLOYEE MENU
    public void employeeMenu() {

        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n------Employee Menu------");
            System.out.println("[1] File Leave Request");
            System.out.println("[2] View Leave Status");
            System.out.println("[3] View Leave Balance");
            System.out.println("[4] Logout");
            System.out.print("Choose Option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    openLeaveForm();
                    break;
                case 2:
                    viewMyLeaveStatus();
                    break;
                case 3:
                    viewMyBalance();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    isLoggedIn = false;
                    currentUser = null;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    //SUPERVISOR MENU
    public void supervisorMenu() {

        boolean isLoggedIn = true;

        boolean hasViewedPending = false; // ✅ NEW

        while (isLoggedIn) {

            System.out.println("\n------Supervisor Menu------");
            System.out.println("[1] File Leave Request");
            System.out.println("[2] View Leave Status");
            System.out.println("[3] View Leave Balance");
            System.out.println("[4] View Pending Leave Requests");
            System.out.println("[5] Approve Leave Request");
            System.out.println("[6] Reject Leave Request");
            System.out.println("[7] Logout");
            System.out.print("Choose option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    openLeaveForm();
                    break;

                case 2:
                    viewMyLeaveStatus();
                    break;

                case 3:
                    viewMyBalance();
                    break;

                case 4:
                    viewPendingRequests();
                    hasViewedPending = true;
                    break;

                case 5:

                    if (!hasViewedPending) {
                        System.out.println("You must view pending leave requests first (Option 4).");

                    } else {
                        approveRequestFlow("Supervisor Approved");
                    }
                    break;

                case 6:

                    if (!hasViewedPending) {
                        System.out.println("You must view pending leave requests first (Option 4).");

                    } else {
                        rejectRequestFlow("Supervisor Rejected");
                    }
                    break;

                case 7:
                    System.out.println("Logging out...");
                    isLoggedIn = false;
                    currentUser = null;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    //HR ADMIN MENU
    public void hrAdminMenu() {

        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n------HR Admin Menu------");
            System.out.println("[1] File Leave Request");
            System.out.println("[2] View Leave Status");
            System.out.println("[3] View Leave Balance");
            System.out.println("[4] View All Leave Records");
            System.out.println("[5] Generate Leave Report");
            System.out.println("[6] Logout");
            System.out.print("Choose option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    openLeaveForm();
                    break;

                case 2:
                    viewMyLeaveStatus();
                    break;

                case 3:
                    viewMyBalance();
                    break;
                case 4:
                    viewAllRecords();
                    break;

                case 5:
                    generateReportMenu();
                    break;

                case 6:
                    System.out.println("Logging out...");
                    isLoggedIn = false;
                    currentUser = null;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }


    private void openLeaveForm() {

        if (currentUser == null) {
            System.out.println("No user logged in.");
            return;
        }

        System.out.println("\n--- LEAVE REQUEST FORM ---");
        System.out.print("Enter Request ID: ");
        int requestId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Leave Type (VL/SL/EL): ");
        String type = sc.nextLine();

        System.out.print("Enter Start Date (e.g., 2026-03-02): ");
        String startDate = sc.nextLine();

        System.out.print("Enter End Date (e.g., 2026-03-05): ");
        String endDate = sc.nextLine();

        System.out.print("Enter Number of Days: ");
        int days = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Reason: ");
        String reason = sc.nextLine();

        LeaveRequest req = new LeaveRequest(
                requestId,
                currentUser.getId(),
                type,
                startDate,
                endDate,
                days,
                reason
        );

        leaveService.addRequest(req);

        System.out.println("\nLeave request submitted successfully!");
    }
    private void viewMyLeaveStatus() {

        if (currentUser == null) return;
        System.out.println("\n====== MY LEAVE REQUESTS ======");

        boolean found = false;

        for (LeaveRequest r : leaveService.getAllRequests()) {

            if (r.getEmployeeId() == currentUser.getId()) {
                r.displayRequest();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No leave requests found.");
        }
    }

    private void viewMyBalance() {

        if (currentUser == null) return;
        System.out.println("\n=== MY LEAVE BALANCE ===");
        currentUser.viewBalances();

    }
    private void viewPendingRequests() {

        System.out.println("\n=== PENDING REQUESTS ===");
        boolean found = false;

        for (LeaveRequest r : leaveService.getAllRequests()) {

            if (r.getStatus().equalsIgnoreCase("Pending")) {
                r.displayRequest();
                found = true;

            }
        }

        if (!found) {
            System.out.println("No pending requests.");
        }
    }

    private void approveRequestFlow(String approvedStatus) {
        System.out.print("Enter Request ID to approve: ");
        int requestId = sc.nextInt();
        sc.nextLine();

        boolean ok = approvalService.approve(requestId, approvedStatus);

        if (!ok) System.out.println("Request not found.");
    }
    private void rejectRequestFlow(String rejectedStatus) {

        System.out.print("Enter Request ID to reject: ");
        int requestId = sc.nextInt();
        sc.nextLine();

        boolean ok = approvalService.reject(requestId, rejectedStatus);

        if (!ok) System.out.println("Request not found.");
    }

    private void viewAllRecords() {
        System.out.println("\n====== ALL LEAVE RECORDS ======");

        if (leaveService.getAllRequests().isEmpty()) {
            System.out.println("No records yet.");
            return;
        }

        for (LeaveRequest r : leaveService.getAllRequests()) {
            r.displayRequest();
        }
    }

    private void generateReportMenu() {
        System.out.println("\n------ REPORT MENU ------");
        System.out.println("[1] All Requests");
        System.out.println("[2] Pending Requests");
        System.out.println("[3] Approved Requests");
        System.out.println("[4] Rejected Requests");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                reportService.generateAllRequestsReport(leaveService.getAllRequests());
                break;

            case 2:
                reportService.generatePendingRequestsReport(leaveService.getAllRequests());
                break;

            case 3:
                reportService.generateApprovedRequestsReport(leaveService.getAllRequests());
                break;

            case 4:
                reportService.generateRejectedRequestsReport(leaveService.getAllRequests());
                break;

            default:
                System.out.println("Invalid option!");
        }
    }
}