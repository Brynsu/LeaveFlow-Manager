public abstract class LeaveType{
    private String leaveCode;
    private String leaveName;
    private int maxDaysPerYear;
    private int advancedNoticeDays;
    private boolean isPaid;

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

    public boolean getIsPaid(){

        return isPaid;
    }
}