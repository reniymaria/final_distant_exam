package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public interface SubjectDao {
    String SQL_FIND_ALL_SUBJECTS = "SELECT * FROM subjects Group by subject";
    String SQL_FIND_AVAILABLE_SUBJECTS = "SELECT id, subject FROM subjects WHERE id NOT IN (SELECT subjects_id FROM marks WHERE users_id = ?)";
    String SQL_DELETE_SUBJECT = "DELETE FROM subjects WHERE id=?";
    String SQL_UPDATE_SUBJECT = "UPDATE subjects SET subject = ? WHERE id =?";
    String SQL_ADD_SUBJECT = "INSERT INTO subjects (subject) VALUE (?)";
    String SQL_SUBJECT_BY_ID = "SELECT subject FROM subjects WHERE id =?";

    List<Subject> getAllsubjects() throws DaoException;

    int getSubjectId(String subject) throws DaoException;

    List<Subject> getStudentAvailableSubjects(int studentId) throws DaoException;

    int getSizeStudentAvailableSubjects(int studentId) throws DaoException;

    List<Subject> numberOfStudentSubjects(int studentId, int offset, int records) throws DaoException;

    List<Subject> numberOfAllSubjects(int offset, int records) throws DaoException;

    int getSizeAllSubjects() throws DaoException;

    void deleteSubject(int subjectId) throws DaoException;

    void updateSubject(int subjectId, String value) throws DaoException;

    void addSubject(String subject) throws DaoException;

    String getSubjectById(int id) throws DaoException;

}
