package model.entities;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class EmployeesKeyClass implements Serializable {
    @QuerySqlField
    private String id;
    @QuerySqlField
    private String name;
    @QuerySqlField
    private Date datebirth;

    public EmployeesKeyClass() {
    }

    public EmployeesKeyClass(String id, String name, Date datebirth) {
        this.id = id;
        this.name = name;
        this.datebirth = datebirth;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDatebirth() {
        return datebirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeesKeyClass that = (EmployeesKeyClass) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(datebirth, that.datebirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, datebirth);
    }

    @Override
    public String toString() {
        return "model.entities.EmployeesKeyClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", datebirth=" + datebirth +
                '}';
    }
}
