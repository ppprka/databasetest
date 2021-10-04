package ru.danko.testtask.entity;

import java.util.StringJoiner;

public class Enroll {
    private long id;
    private Student student;
    private StudentGroup studentGroup;

    public Enroll() {}

    public Enroll(long id, Student student, StudentGroup studentGroup) {
        this.id = id;
        this.student = student;
        this.studentGroup = studentGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Enroll enroll = (Enroll) o;
        if (id != enroll.id) {
            return false;
        }
        if (student != null ? !student.equals(enroll.student) : enroll.student != null) {
            return false;
        }
        return studentGroup != null ? studentGroup.equals(enroll.studentGroup) : enroll.studentGroup == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (studentGroup != null ? studentGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Enroll.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("student=" + student)
                .add("studentGroup=" + studentGroup)
                .toString();
    }
}
