package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;

import java.util.List;

public interface MarkDao {

    String SQL_ADD_MARK = "INSERT INTO `marks` (`mark`, `subjects_id`, `users_id`) VALUES (?, ?, ?); ";
    String SQL_EXAM_MARKS = "SELECT u.name, u.surname, s.subject, m.mark, m.subjects_id, m.users_id FROM users u INNER JOIN marks m ON u.id = m.users_id INNER JOIN subjects s ON s.id = m.subjects_id";
    String SQL_DELETE_MARK = "DELETE FROM marks WHERE subjects_id = ? AND users_id = ?";

    void addMark(int mark, int studentId, int subjectId) throws DaoException;

    List<ExamResult> getExamMarks() throws DaoException;

    List<ExamResult> numberOfMarks(int offset, int records) throws DaoException;

    int allMarks() throws DaoException;

    List<ExamResult> numberOfStudentMarks(int studentId, int offset, int records) throws DaoException;

    int allStudentMarks(int studentId) throws DaoException;

    void deleteMark(int userId, int subjectId) throws DaoException;


}
