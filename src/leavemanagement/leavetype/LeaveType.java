package leavemanagement.leavetype;

public interface LeaveType {

    String getLeaveCode();

    String getLeaveName();

    int getMaxDaysPerYear();

    int getAdvancedNoticeDays();

    boolean isPaid();
}
