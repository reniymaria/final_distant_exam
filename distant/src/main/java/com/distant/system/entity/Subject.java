package com.distant.system.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject1 = (Subject) o;
        return subjectID == subject1.subjectID &&
                Objects.equals(subject, subject1.subject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subjectID, subject);
    }
}
