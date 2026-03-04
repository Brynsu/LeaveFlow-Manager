package leavemanagement.leavetype;

//changed from Abstract to Interface
public interface LeaveType {

    String getLeaveCode();

    String getLeaveName();

    int getMaxDaysPerYear();

    int getAdvancedNoticeDays();

    boolean isPaid();
}
