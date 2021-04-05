package by.nintendo.diplomot.entity;

public enum UserSpecialization {
    JAVA_PROGRAMMER("Java programmer")
    ,JAVASCRIPT_PROGRAMMER("JavaScript programmer");
    private String item;

    UserSpecialization(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
