package kls.ignitenode.model.entities;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.Date;

public class PersonKeyClass implements Serializable {
    @QuerySqlField
    private String id;
    @QuerySqlField
    private String name;
    @QuerySqlField
    private Date datebirth;
    @QuerySqlField
    private boolean isMarried;
    @QuerySqlField
    private String telephone;
    @QuerySqlField
    private int age;

    public PersonKeyClass() {
    }

    public PersonKeyClass(String id, String name, Date datebirth, boolean isMarried, String telephone, int age) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(Date datebirth) {
        this.datebirth = datebirth;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "model.entities.PersonKeyClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", datebirth=" + datebirth +
                ", isMarried=" + isMarried +
                ", telephone='" + telephone + '\'' +
                ", age=" + age +
                ", married=" + isMarried() +
                '}';
    }
}
