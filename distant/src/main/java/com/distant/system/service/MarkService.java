package com.distant.system.service;

import com.distant.system.entity.dto.ExamResult;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

import java.sql.Timestamp;
import java.util.List;

/**
 * The Interface MarkService
 *
 * defines methods for working with marks.
 */
public interface MarkService {

    /**
     * @param offset  set of records
     * @param records number of records
     * @return list of exam results
     * @throws ServiceException the exception during getting data from DB
     */
    List<ExamResult> numberOfMarks(int offset, int records) throws ServiceException;

    /**
     * @return size of all marks
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception to validate marks
     */
    int allMarks() throws ServiceException, ValidationException;

    /**
     * @param userId    int id of student
     * @param subjectId id of subject
     * @throws ServiceException the exception during getting data from DB
     */
    void deleteMark(int userId, int subjectId) throws ServiceException;

    /**
     * @param studentId int id of student
     * @param offset    set of records
     * @param records   number of records
     * @return list of exam results
     * @throws ServiceException the exception during getting data from DB
     */
    List<ExamResult> numberOfStudentMarks(int studentId, int offset, int records) throws ServiceException;

    /**
     * @param studentId int id of student
     * @return size of marks for student
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception to validate marks
     */
    int allStudentMarks(int studentId) throws ServiceException, ValidationException;

    /**
     * @param mark      int id of mark
     * @param studentId id of student
     * @param subjectId id of subject
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception to validate mark
     */
    void addMark(int mark, int studentId, int subjectId) throws ServiceException, ValidationException;

}
