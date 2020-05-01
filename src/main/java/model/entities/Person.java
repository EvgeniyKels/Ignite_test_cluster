package model.entities;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.sql.Timestamp;

public class Person implements Serializable {
    @QuerySqlField
    private long count;
    @QuerySqlField
    private Timestamp timestamp;

    public Person() {
    }

    public Person(long count, Timestamp timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    public long getCount() {
        return count;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}