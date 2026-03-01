public class RegularEmployee extends Employee {

    // CONSTRUCTOR [ CALLS Employee CONSTRUCTOR ]

    public RegularEmployee(int id, String name, String department, String email, int password, int vacationBal, int sickBal, int emergencyBal){
        super(id, name, department, email, password, vacationBal, sickBal, emergencyBal);
    }

    // CREATE A LEAVE REQUEST

    public LeaveRequest createLeaveRequest(int requestId, String type, String startDate, String endDate, String reason){
        LeaveRequest req = new LeaveRequest(
                requestId,
                this.getId(),
                type,
                startDate,
                endDate,
                reason
        );

        return  req;

    }

    // SUBMIT A LEAVE REQUEST

    public void submitRequest(LeaveRequest req){
        req.setStatus("Submitted");
        System.out.println("Leave request " + req.getRequestId() + " submitted.");
    }

}