package com.distant.system.entity;

import java.io.Serializable;
import java.util.Objects;

public class Mark implements Serializable {

    private int mark;
    private int studentId;
    private int subjectId;

    public Mark() {
    }

    public Mark(int mark, int studentId, int subjectId) {
        this.mark = mark;
        this.studentId = studentId;
        this.subjectId = subjectId;
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
                subjectId == mark1.subjectId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(mark, studentId, subjectId);
    }
}
