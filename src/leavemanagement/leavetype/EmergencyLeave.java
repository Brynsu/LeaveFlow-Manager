package leavemanagement.leavetype;

// EMERGENCY LEAVE
public class EmergencyLeave implements LeaveType {

    private String leaveCode = "EL";
    private String leaveName = "Emergency Leave";
    private int maxDaysPerYear = 5;
    private int advancedNoticeDays = 0;
    private boolean isPaid = false;

    public String getLeaveCode() {
        return leaveCode;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public int getMaxDaysPerYear() {
        return maxDaysPerYear;
    }

    public int getAdvancedNoticeDays() {
        return advancedNoticeDays;
    }

    public boolean isPaid() {
        return isPaid;
    }
}