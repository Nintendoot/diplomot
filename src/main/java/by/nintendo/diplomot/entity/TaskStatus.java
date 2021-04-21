package by.nintendo.diplomot.entity;

public enum TaskStatus {
    NOT_STARTED("Not started")
    ,IN_PROGRESS("In progree")
    ,CHEKING("Cheking")
    ,COMPLETED("Completed")
    ,POSTPONED("Postponed");

    private String iteam;

    TaskStatus(String iteam) {
        this.iteam = iteam;
    }

    public String getIteam() {
        return iteam;
    }

    public void setIteam(String iteam) {
        this.iteam = iteam;
    }
}
