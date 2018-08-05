package com.distant.system.service;

import com.distant.system.entity.Subject;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

import java.util.List;

/**
 * The Interface SubjectService
 *
 * defines methods for working with subjects.
 */
public interface SubjectService {

    /**
     * @param studentId int id of student
     * @return size of subjects
     * @throws ServiceException the exception during getting data from DB
     */
    int getSizeStudentAvailableSubjects(int studentId) throws ServiceException;

    /**
     * @param studentId int id of student
     * @param offset    set of records
     * @param records   number of records
     * @return list of subjects
     * @throws ServiceException the exception during getting data from DB
     */
    List<Subject> numberOfStudentSubjects(int studentId, int offset, int records) throws ServiceException;

    /**
     * @param offset  set of recorts
     * @param records number of records
     * @return list of subjects
     * @throws ServiceException the exception during getting data from DB
     */
    List<Subject> numberOfAllSubjects(int offset, int records) throws ServiceException;

    /**
     * @return size of all subjects
     * @throws ServiceException the exception during getting data from DB
     */
    int getSizeAllSubjects() throws ServiceException;

    /**
     * @param subjectId int id of subject
     * @throws ServiceException the exception during getting data from DB
     */
    void deleteSubject(int subjectId) throws ServiceException;

    /**
     * @param subject the name of subject
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception to validate subject
     */
    void addSubject(String subject) throws ServiceException, ValidationException;


    /**
     * @param subjectId int id of subject
     * @param value     name of the subject
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception tovalidate updated subject
     */
    void updateSubject(int subjectId, String value) throws ServiceException, ValidationException;

    /**
     * @param id int id of subject
     * @return subject name
     * @throws ServiceException the exception during getting data from DB
     */
    String getSubjectById(int id) throws ServiceException;
}
