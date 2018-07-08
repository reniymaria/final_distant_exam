package com.distant.system.entity;

import java.io.Serializable;

public class Subject implements Serializable {

    private int subjectID;
    private String subject;

    public Subject(int subjectID, String subject) {
        this.subjectID = subjectID;
        this.subject = subject;
    }

    public Subject() {
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public String toString() {
        return subject;

    }
}
