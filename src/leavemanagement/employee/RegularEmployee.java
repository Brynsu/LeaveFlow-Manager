package leavemanagement.employee;
import leavemanagement.request.LeaveRequest;

public class RegularEmployee extends Employee {

    // CONSTRUCTOR [ CALLS Employee CONSTRUCTOR ]

    public RegularEmployee(int id, String name, String department, String email) {
        super(id, name, department, email);
    }
}