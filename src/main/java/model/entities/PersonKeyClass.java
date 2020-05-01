package model.entities;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.sql.Timestamp;

public class PersonKeyClass {
    @QuerySqlField
    private String id;
    @QuerySqlField
    private String name;
    @QuerySqlField
    private Timestamp datebirth;
    @QuerySqlField
    private boolean isMarried;
    @QuerySqlField
    private String telephone;
    @QuerySqlField
    private int age;

    public PersonKeyClass() {
    }

    public PersonKeyClass(String id, String name, Timestamp datebirth, boolean isMarried, String telephone, int age) {
        this.id = id;
        this.name = name;
        this.datebirth = datebirth;
        this.isMarried = isMarried;
        this.telephone = telephone;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getDatebirth() {
        return datebirth;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getAge() {
        return age;
    }
}
