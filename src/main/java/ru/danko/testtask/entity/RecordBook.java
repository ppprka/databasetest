package ru.danko.testtask.entity;

import java.util.StringJoiner;

public class RecordBook {
    private long id;
    private String graduationYear;
    private String group_id;

    public RecordBook() {
    }

    public RecordBook(long id, String graduationYear, String group_id) {
        this.id = id;
        this.graduationYear = graduationYear;
        this.group_id = group_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordBook that = (RecordBook) o;
        if (id != that.id){
            return false;
        }
        if (graduationYear != null ? !graduationYear.equals(that.graduationYear) : that.graduationYear != null) {
            return false;
        }
        return group_id != null ? group_id.equals(that.group_id) : that.group_id == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (graduationYear != null ? graduationYear.hashCode() : 0);
        result = 31 * result + (group_id != null ? group_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RecordBook.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("graduationYear='" + graduationYear + "'")
                .add("group_id='" + group_id + "'")
                .toString();
    }
}
