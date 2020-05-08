package model.entities;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.Objects;

public class Employees implements Serializable {
    @QuerySqlField
    private long count;

    public Employees() {
    }

    public Employees(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return count == employees.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
