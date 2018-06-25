package com.distant.system.entity;

public class Mark {
    private int mark;
    private int studentId;
    private int subjectId;

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


}
