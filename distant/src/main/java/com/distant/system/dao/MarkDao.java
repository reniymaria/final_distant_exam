package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;

import java.sql.Timestamp;
import java.util.List;

/**
 * The Interface MarkDao
 *
 * defines methods for working with marks.
 */
public interface MarkDao {

    String SQL_ADD_MARK = "INSERT INTO `marks` (`mark`, `subjects_id`, `users_id`, `date`) VALUES (?, ?, ?, NOW()); ";
    String SQL_EXAM_MARKS = "SELECT u.name, u.surname, s.subject, m.mark, m.subjects_id, m.users_id, m.date FROM users u INNER JOIN marks m ON u.id = m.users_id INNER JOIN subjects s ON s.id = m.subjects_id";
    String SQL_DELETE_MARK = "DELETE FROM marks WHERE subjects_id = ? AND users_id = ?";

    /**
     * @param mark      int id of mark
     * @param studentId id of student
     * @param subjectId id of subject
     * @throws DaoException the exception during getting data from DB
     */
    void addMark(int mark, int studentId, int subjectId) throws DaoException;

    /**
     * @return list of exam results
     * @throws DaoException the exception during getting data from DB
     */
    List<ExamResult> getExamMarks() throws DaoException;

    /**
     * @param offset  set of records
     * @param records number of records
     * @return list of exam results
     * @throws DaoException the exception during getting data from DB
     */
    List<ExamResult> numberOfMarks(int offset, int records) throws DaoException;

    /**
     * @return size of all marks
     * @throws DaoException the exception during getting data from DB
     */
    int allMarks() throws DaoException;

    /**
     * @param studentId int id of student
     * @param offset    set of records
     * @param records   number of records
     * @return list of exam results
     * @throws DaoException the exception during getting data from DB
     */
    List<ExamResult> numberOfStudentMarks(int studentId, int offset, int records) throws DaoException;

    /**
     * @param studentId int id of student
     * @return size of marks for student
     * @throws DaoException the exception during getting data from DB
     */
    int allStudentMarks(int studentId) throws DaoException;

    /**
     * @param userId    int id of student
     * @param subjectId id of subject
     * @throws DaoException the exception during getting data from DB
     */
    void deleteMark(int userId, int subjectId) throws DaoException;


}
