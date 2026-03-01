package leavemanagement.leavetype;

public abstract class LeaveType{
    private String leaveCode;
    private String leaveName;
    private int maxDaysPerYear;
    private int advancedNoticeDays;
    private boolean isPaid;

    public LeaveType(String leaveCode, String leaveName, int maxDaysPerYear, int advancedNoticeDays, boolean isPaid){
        this.leaveCode = leaveCode;
        this.leaveName = leaveName;
        this.maxDaysPerYear = maxDaysPerYear;
        this.advancedNoticeDays = advancedNoticeDays;
        this.isPaid = isPaid;
    }

    public String getLeaveCode(){
        return leaveCode;
    }

    public String getLeaveName(){
        return leaveName;
    }

    public int getMaxDaysPerYear(){
        return maxDaysPerYear;
    }

    public int getAdvancedNoticeDays(){
        return advancedNoticeDays;
    }

    public boolean issPaid(){

        return isPaid;
    }
}