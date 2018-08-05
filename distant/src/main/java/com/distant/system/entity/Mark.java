package com.distant.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Mark implements Serializable {

    private int mark;
    private int studentId;
    private int subjectId;
    private Date date;

    public Mark() {
    }

    public Mark(int mark, int studentId, int subjectId, Date date) {
        this.mark = mark;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark1 = (Mark) o;
        return mark == mark1.mark &&
                studentId == mark1.studentId &&
                subjectId == mark1.subjectId &&
                Objects.equals(date, mark1.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mark, studentId, subjectId, date);
    }
}
