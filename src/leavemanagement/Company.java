package leavemanagement;
import java.util.ArrayList;
import leavemanagement.employee.Employee;
import leavemanagement.employee.RegularEmployee;
import leavemanagement.employee.Supervisor;
import leavemanagement.employee.HRAdmin;

public class Company {
    private ArrayList<Employee> employees;

    public Company() {
        employees = new ArrayList<>();

    }
    public Employee findEmployeeById(int id) {

        for (Employee e : employees) {

            if (e.getId() == id) return e;
        }

        return null;
    }

    public Employee getOrCreateEmployee(int id, String name, String dept, String email, String role) {

        Employee existing = findEmployeeById(id);

        if (existing != null) return existing;

        Employee created;

        if (role.equalsIgnoreCase("SUPERVISOR")) {

            created = new Supervisor(id, name, dept, email);

        } else if (role.equalsIgnoreCase("HR")) {

            created = new HRAdmin(id, name, dept, email);

        } else {

            created = new RegularEmployee(id, name, dept, email);

        }

        employees.add(created);
        return created;
    }
}