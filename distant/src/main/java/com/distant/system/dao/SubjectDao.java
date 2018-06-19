package com.distant.system.dao;

import com.distant.system.entity.Subject;

import java.util.ArrayList;

public interface SubjectDao {
    String SQL_FIND_ALL_SUBJECTS = "SELECT * FROM subjects Group by subject";
    String SQL_ADD_SUBJECT = "INSERT INTO subjects (subject, studentId) VALUES (?, ?)";


    ArrayList<Subject> getAllsubjects() ;

    int getSubjectId(String subject);

    void addSubjectForStudent(int studentId, String subject);
}
