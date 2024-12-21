package dev.m2t.dbmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "databases")
public class Database {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private DatabaseLevelEnum level;

    public Database() {
    }

    public Database(Long id, String name, DatabaseLevelEnum level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DatabaseLevelEnum getLevel() {
        return level;
    }

    public void setLevel(DatabaseLevelEnum level) {
        this.level = level;
    }
}
