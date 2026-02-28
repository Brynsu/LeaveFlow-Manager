public abstract class LeaveType{

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