package dev.m2t.dbmanager.dto.request;

public class CreateDatabaseRequestDto {
    private String name;

    public CreateDatabaseRequestDto() {

    }

    public CreateDatabaseRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
