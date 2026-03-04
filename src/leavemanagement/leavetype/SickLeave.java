package leavemanagement.leavetype;

// SICK LEAVE
public class SickLeave implements LeaveType {

    private String leaveCode = "SL";
    private String leaveName = "Sick Leave";
    private int maxDaysPerYear = 10;
    private int advancedNoticeDays = 0;
    private boolean isPaid = true;

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
