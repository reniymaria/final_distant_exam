package com.distant.system.entity;

public class Mark {
    private int studentId;
    private int subjectId;

    public Mark(int studentId, int subjectId) {

        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public Mark(){}

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
    public String toString() {
        return "Mark{" +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                '}';
    }
}
