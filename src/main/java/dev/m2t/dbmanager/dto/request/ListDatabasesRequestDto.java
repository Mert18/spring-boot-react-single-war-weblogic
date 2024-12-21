package dev.m2t.dbmanager.dto.request;

import dev.m2t.dbmanager.model.DatabaseLevelEnum;

public class ListDatabasesRequestDto {
    private String name;
    private Integer level;

    public ListDatabasesRequestDto() {
    }

    public ListDatabasesRequestDto(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
