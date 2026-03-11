package leavemanagement;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import leavemanagement.employee.Employee;
import leavemanagement.request.LeaveRequest;
import leavemanagement.service.LeaveService;
import leavemanagement.service.ApprovalService;
import leavemanagement.service.BalanceService;
import leavemanagement.service.ReportService;
import leavemanagement.leavetype.LeaveType;
import leavemanagement.leavetype.VacationLeave;
import leavemanagement.leavetype.SickLeave;
import leavemanagement.leavetype.EmergencyLeave;

//nag add Try Catch
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
            System.out.println("\n==================================================");
            System.out.println("                LEAVE FLOW MANAGER");
            System.out.println("==================================================\n");
            System.out.println("--------------------------------------------------");
            System.out.println("                      LOGIN");
            System.out.println("--------------------------------------------------");

            int id;

            try {
                System.out.print("Enter Employee ID: ");
                id = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("\nError: Employee ID must be a number.");
                sc.nextLine();
                continue;
            }

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
                currentUser.resetBalancesIfNewYear();

                System.out.println("\nLogin Successful as Employee!");
                employeeMenu();

            } else if (firstDigit == '2' && !department.equalsIgnoreCase("HR")) {
                currentUser = company.getOrCreateEmployee(id, name, department, email, "SUPERVISOR");
                currentUser.resetBalancesIfNewYear();

                System.out.println("\nLogin Successful as Supervisor!");
                supervisorMenu();

            } else if (firstDigit == '3' && department.equalsIgnoreCase("HR")) {
                currentUser = company.getOrCreateEmployee(id, name, department, email, "HR");
                currentUser.resetBalancesIfNewYear();

                System.out.println("\nLogin Successful as HR Admin!");
                hrAdminMenu();

            } else {
                currentUser = null;
                System.out.println("\nInvalid credentials based on company rules.");
            }

            System.out.println("\n--------------------------------------------------");
            System.out.print("Do you want to login again? (yes/no): ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("no")) {
                systemRunning = false;
                System.out.println("\nExiting system...");
            }
        }
    }

    // EMPLOYEE MENU
    public void employeeMenu() {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n==================================================");
            System.out.println("                   EMPLOYEE MENU");
            System.out.println("==================================================");

            System.out.println("[1] File Leave Request");
            System.out.println("[2] View Leave Status");
            System.out.println("[3] View Leave Balance");
            System.out.println("[4] Logout");

            int option;

            try {
                System.out.print("\nChoose Option: ");
                option = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("\nError: Please enter a valid number.");
                sc.nextLine();
                continue;
            }

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
                    System.out.println("\nLogging out...");
                    isLoggedIn = false;
                    currentUser = null;
                    break;

                default:
                    System.out.println("\nInvalid option!");
            }
        }
    }

    // SUPERVISOR MENU
    public void supervisorMenu() {
        boolean isLoggedIn = true;
        boolean hasViewedPending = false;

        while (isLoggedIn) {
            System.out.println("\n==================================================");
            System.out.println("                 SUPERVISOR MENU");
            System.out.println("==================================================");

            System.out.println("[1] File Leave Request");
            System.out.println("[2] View Leave Status");
            System.out.println("[3] View Leave Balance");
            System.out.println("[4] View Pending Leave Requests");
            System.out.println("[5] Approve Leave Request");
            System.out.println("[6] Reject Leave Request");
            System.out.println("[7] Logout");

            int option;

            try {
                System.out.print("\nChoose option: ");
                option = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("\nError: Please enter a valid number.");
                sc.nextLine();
                continue;
            }

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
                        System.out.println("\nYou must view pending leave requests first (Option 4).");
                    } else {
                        approveRequestFlow();
                    }
                    break;

                case 6:
                    if (!hasViewedPending) {
                        System.out.println("\nYou must view pending leave requests first (Option 4).");
                    } else {
                        rejectRequestFlow();
                    }
                    break;

                case 7:
                    System.out.println("\nLogging out...");
                    isLoggedIn = false;
                    currentUser = null;
                    break;

                default:
                    System.out.println("\nInvalid option!");
            }
        }
    }

    // HR ADMIN MENU
    public void hrAdminMenu() {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n==================================================");
            System.out.println("                   HR ADMIN MENU");
            System.out.println("==================================================");

            System.out.println("[1] File Leave Request");
            System.out.println("[2] View Leave Status");
            System.out.println("[3] View Leave Balance");
            System.out.println("[4] View All Leave Records");
            System.out.println("[5] Generate Leave Report");
            System.out.println("[6] Logout");

            int option;

            try {
                System.out.print("\nChoose option: ");
                option = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("\nError: Please enter a valid number.");
                sc.nextLine();
                continue;
            }

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
                    System.out.println("\nLogging out...");
                    isLoggedIn = false;
                    currentUser = null;
                    break;

                default:
                    System.out.println("\nInvalid option!");
            }
        }
    }

    private void openLeaveForm() {

        if (currentUser == null) {

            System.out.println("No user logged in.");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("                LEAVE REQUEST FORM");
        System.out.println("==================================================");

        int requestId;

        try {
            System.out.print("Enter Request ID: ");
            requestId = sc.nextInt();
            sc.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Error: Request ID must be a number.");
            sc.nextLine();
            return;
        }

        System.out.print("Enter Leave Type (VL/SL/EL): ");
        String leaveInput = sc.nextLine();

        LeaveType type;

        if (leaveInput.equalsIgnoreCase("VL")) {
            type = new VacationLeave();

        } else if (leaveInput.equalsIgnoreCase("SL")) {
            type = new SickLeave();

        } else if (leaveInput.equalsIgnoreCase("EL")) {
            type = new EmergencyLeave();

        } else {
            System.out.println("Error: Invalid leave type.");
            return;
        }

        LocalDate start;
        LocalDate end;

        try {
            System.out.print("Enter Start Date (YYYY-MM-DD): ");
            String startDate = sc.nextLine();

            System.out.print("Enter End Date (YYYY-MM-DD): ");
            String endDate = sc.nextLine();

            start = LocalDate.parse(startDate);
            end = LocalDate.parse(endDate);

            if (start.isAfter(end)) {

                System.out.println("Error: Start date must be earlier than or equal to end date.");
                return;
            }

            LocalDate filed = LocalDate.now();

            if (filed.plusDays(type.getAdvancedNoticeDays()).isAfter(start)) {
                System.out.println("Error: Leave must be filed at least "
                        + type.getAdvancedNoticeDays()
                        + " days before the start date.");
                return;
            }

            int days;

            try {

                System.out.print("Enter Number of Days: ");
                days = sc.nextInt();
                sc.nextLine();

                if (days <= 0) {

                    System.out.println("Error: Leave days must be greater than zero.");
                    return;

                }

                int availableBalance = 0;

                if (type.getLeaveCode().equalsIgnoreCase("VL")) {
                    availableBalance = currentUser.getVacationBal();

                } else if (type.getLeaveCode().equalsIgnoreCase("SL")) {
                    availableBalance = currentUser.getSickBal();

                } else if (type.getLeaveCode().equalsIgnoreCase("EL")) {
                    availableBalance = currentUser.getEmergencyBal();
                }

                if (days > availableBalance) {
                    System.out.println("Error: Requested days exceed your available "
                            + type.getLeaveName() + " balance.");
                    return;
                }

                System.out.print("Enter Reason: ");
                String reason = sc.nextLine();

                String dateFiled = LocalDate.now().toString();

                LeaveRequest req = new LeaveRequest(
                        requestId,
                        currentUser.getId(),
                        type,
                        dateFiled,
                        start.toString(),
                        end.toString(),
                        days,
                        reason
                );

                leaveService.addRequest(req);

                System.out.println("\nLeave request submitted successfully!");

            } catch (InputMismatchException e) {

                System.out.println("Error: Number of days must be a valid number.");
                sc.nextLine();
            }

        } catch (DateTimeParseException e) {

            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private void viewMyLeaveStatus() {

        if (currentUser == null) {
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("                MY LEAVE REQUESTS");
        System.out.println("==================================================");

        boolean found = false;

        for (LeaveRequest r : leaveService.getAllRequests()) {

            if (r.getEmployeeId() == currentUser.getId()) {

                Employee emp = company.findEmployeeById(r.getEmployeeId());
                r.displayRequest(emp);
                found = true;
            }
        }

        if (!found) {

            System.out.println("No leave requests found.");
        }
    }

    private void viewMyBalance() {

        if (currentUser == null) {
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("                 MY LEAVE BALANCE");
        System.out.println("==================================================");

        currentUser.viewBalances();
    }

    private void viewPendingRequests() {

        System.out.println("\n==================================================");
        System.out.println("                 PENDING REQUESTS");
        System.out.println("==================================================");

        boolean found = false;

        for (LeaveRequest r : leaveService.getAllRequests()) {

            if (r.getStatus().equalsIgnoreCase("Pending")) {
                Employee emp = company.findEmployeeById(r.getEmployeeId());
                r.displayRequest(emp);
                found = true;
            }
        }

        if (!found) {

            System.out.println("No pending requests.");
        }
    }

    private void approveRequestFlow() {

        System.out.println("\n==================================================");
        System.out.println("                 APPROVE REQUEST");
        System.out.println("==================================================");

        int requestId;

        try {
            System.out.print("Enter Request ID to approve: ");
            requestId = sc.nextInt();
            sc.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Error: Request ID must be a number.");
            sc.nextLine();
            return;
        }

        boolean ok = approvalService.approve(requestId);

        if (!ok) {
            System.out.println("Request not found.");
        }
    }

    private void rejectRequestFlow() {
        System.out.println("\n==================================================");
        System.out.println("                  REJECT REQUEST");
        System.out.println("==================================================");

        int requestId;

        try {

            System.out.print("Enter Request ID to reject: ");
            requestId = sc.nextInt();
            sc.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Error: Request ID must be a number.");
            sc.nextLine();
            return;
        }

        boolean ok = approvalService.reject(requestId);

        if (!ok) {
            System.out.println("Request not found.");
        }
    }

    private void viewAllRecords() {

        System.out.println("\n==================================================");
        System.out.println("                ALL LEAVE RECORDS");
        System.out.println("==================================================");

        if (leaveService.getAllRequests().isEmpty()) {

            System.out.println("No records yet.");
            return;
        }

        for (LeaveRequest r : leaveService.getAllRequests()) {

            Employee emp = company.findEmployeeById(r.getEmployeeId());
            r.displayRequest(emp);
        }
    }

    private void generateReportMenu() {

        System.out.println("\n==================================================");
        System.out.println("                    REPORT MENU");
        System.out.println("==================================================");

        System.out.println("[1] All Requests");
        System.out.println("[2] Pending Requests");
        System.out.println("[3] Approved Requests");
        System.out.println("[4] Rejected Requests");

        int choice;

        try {
            System.out.print("\nChoose option: ");
            choice = sc.nextInt();
            sc.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Error: Please enter a valid number.");
            sc.nextLine();
            return;
        }

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