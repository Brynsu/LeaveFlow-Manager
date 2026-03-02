package leavemanagement;

import java.util.*;

public class LeaveManagementSystem {

    private Scanner sc;
    private boolean systemRunning;

    public LeaveManagementSystem() {
        sc = new Scanner(System.in);
        systemRunning = true;
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

                System.out.println("\nLogin Successful as Employee!");
                employeeMenu();

            } else if (firstDigit == '2' && !department.equalsIgnoreCase("HR")) {

                System.out.println("\nLogin Successful as Supervisor!");
                supervisorMenu();

            } else if (firstDigit == '3' && department.equalsIgnoreCase("HR")) {

                System.out.println("\nLogin Successful as HR Admin!");
                hrAdminMenu();

            } else {

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
                    System.out.println("Filing leave request...");
                    break;

                case 2:
                    System.out.println("Opening leave status page...");
                    break;

                case 3:
                    System.out.println("Opening leave balance page...");
                    break;

                case 4:
                    System.out.println("Logging out...");
                    isLoggedIn = false;   // FIXED
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public void supervisorMenu() {

        boolean isLoggedIn = true;

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
                    System.out.println("Filing leave request...");
                    break;

                case 2:
                    System.out.println("Opening leave status page...");
                    break;

                case 3:
                    System.out.println("Opening leave balance page...");
                    break;

                case 4:
                    System.out.println("Viewing pending requests...");
                    break;

                case 5:
                    System.out.println("Approving request...");
                    break;

                case 6:
                    System.out.println("Rejecting request...");
                    break;

                case 7:
                    System.out.println("Logging out...");
                    isLoggedIn = false;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

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
                    System.out.println("Filing leave request...");
                    break;

                case 2:
                    System.out.println("Opening leave status page...");
                    break;

                case 3:
                    System.out.println("Opening leave balance page...");
                    break;

                case 4:
                    System.out.println("Viewing all leave records...");
                    break;

                case 5:
                    System.out.println("Generating report...");
                    break;

                case 6:
                    System.out.println("Logging out...");
                    isLoggedIn = false;
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}