package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * The Interface SubjectDao
 *
 * defines methods for working with subjects.
 */

public interface SubjectDao {
    String SQL_FIND_ALL_SUBJECTS = "SELECT * FROM subjects Group by subject";
    String SQL_FIND_AVAILABLE_SUBJECTS = "SELECT id, subject FROM subjects WHERE id NOT IN (SELECT subjects_id FROM marks WHERE users_id = ?)";
    String SQL_DELETE_SUBJECT = "DELETE FROM subjects WHERE id=?";
    String SQL_UPDATE_SUBJECT = "UPDATE subjects SET subject = ? WHERE id =?";
    String SQL_ADD_SUBJECT = "INSERT INTO subjects (subject) VALUE (?)";
    String SQL_SUBJECT_BY_ID = "SELECT subject FROM subjects WHERE id =?";

    /**
     * @return List of subjects
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */

    List<Subject> getAllsubjects() throws DaoException;
    /**
     * @param studentId int id of student
     *
     * @return list of subjects that student can see
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */

    List<Subject> getStudentAvailableSubjects(int studentId) throws DaoException;
    /**
     * @param studentId int id of student
     *
     * @return size of subjects
     *
     * @throws DaoException
     *             the exception during getting data from DB
     */

    int getSizeStudentAvailableSubjects(int studentId) throws DaoException;
    /**
     * @param studentId int id of student
     * @param offset set of records
     * @param records number of records
     * @return list of subjects
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */

    List<Subject> numberOfStudentSubjects(int studentId, int offset, int records) throws DaoException;
    /**
     * @param offset set of recorts
     *
     * @param records number of records
     *
     * @return list of subjects
     *
     * @throws DaoException
     *             the exception during getting data from DB
     */

    List<Subject> numberOfAllSubjects(int offset, int records) throws DaoException;
    /**

     * @return size of all subjects
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */

    int getSizeAllSubjects() throws DaoException;
    /**
     * @param subjectId int id of subject
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */

    void deleteSubject(int subjectId) throws DaoException;
    /**
     * @param subjectId int id of subject
     *
     *@param value name of the subject
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */

    void updateSubject(int subjectId, String value) throws DaoException;
    /**
     * @param subject the name of subject
     *
     * @throws DaoException
     *             the exception during getting data from DB
     */

    void addSubject(String subject) throws DaoException;
    /**
     * @param id int id of subject
     *
     * @return subject name
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */

    String getSubjectById(int id) throws DaoException;

}
