package dev.m2t.dbmanager.model;

public enum DatabaseLevelEnum {
    OTHER(-1, "OTHER", "Other"),
    DEV(0, "DEV", "Development"),
    TEST(1, "TEST", "Test"),
    PROD(2, "PROD", "Production");

    private Integer code;
    private String label;
    private String description;

    DatabaseLevelEnum(Integer code, String label, String description) {
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public static DatabaseLevelEnum fromCode(Integer code) {
        for(DatabaseLevelEnum dle: DatabaseLevelEnum.values()) {
            if(dle.getCode().equals(code)) {
                return dle;
            }
        }
        return DatabaseLevelEnum.OTHER;
    }

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}
