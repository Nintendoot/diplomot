package by.nintendo.diplomot.entity;

public enum Priority {
    LOW("Low")
    ,MEDIUM("Medium")
    ,HIGH("High");

    private String iteam;

    Priority(String iteam) {
        this.iteam = iteam;
    }

    public String getIteam() {
        return iteam;
    }

    public void setIteam(String iteam) {
        this.iteam = iteam;
    }
}
