package kls.ignitenode.model.entities;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Person implements Serializable {
    @QuerySqlField
    private long count;
    @QuerySqlField
    private Date timestamp;

    public Person() {
    }

    public Person(long count, Date timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return count == person.count &&
                Objects.equals(timestamp, person.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, timestamp);
    }

    @Override
    public String toString() {
        return "model.entities.Person{" +
                "count=" + count +
                ", timestamp=" + timestamp +
                '}';
    }
}