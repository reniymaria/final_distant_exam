package com.distant.system.dao.mysql.impl;

import com.distant.system.dao.MarkDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.AbstractDAO;
import com.distant.system.dao.util.DaoUtil;
import com.distant.system.entity.dto.ExamResult;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class InMemoryMarkDao extends AbstractDAO implements MarkDao {

    @Override
    public void addMark(int mark, int studentId, int subjectId) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_ADD_MARK);
            statement.setInt(1, mark);
            statement.setInt(2, subjectId);
            statement.setInt(3, studentId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during adding mark", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<ExamResult> getExamMarks() throws DaoException {
        List<ExamResult> examList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_EXAM_MARKS);
            rs = statement.executeQuery();
            while (rs.next()) {
                ExamResult examResult = new ExamResult();
                examResult.setName(rs.getString("name"));
                examResult.setSurname(rs.getString("surname"));
                examResult.setSubject(rs.getString("subject"));
                examResult.setMark(rs.getInt("mark"));
                examResult.setSubjectID(rs.getInt("subjects_id"));
                examResult.setUserID(rs.getInt("users_id"));
                examList.add(examResult);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during finding question", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return examList;
    }

    @Override
    public List<ExamResult> numberOfMarks(int offset, int records) throws DaoException {
        List<ExamResult> allMarks = getExamMarks();
        return DaoUtil.numberOfStrings(allMarks, offset, records);
    }

    @Override
    public int allMarks() throws DaoException {
        List<ExamResult> allMarks = getExamMarks();
        return allMarks.size();
    }

    @Override
    public List<ExamResult> numberOfStudentMarks(int studentId, int offset, int records) throws DaoException {
        List<ExamResult> allMarks = getExamMarks();
        List<ExamResult> studentResult = new ArrayList<>();
        for (ExamResult examResult : allMarks) {
            if (examResult.getUserID() == studentId) {
                studentResult.add(examResult);
            }
        }
        return DaoUtil.numberOfStrings(studentResult, offset, records);
    }

    @Override
    public int allStudentMarks(int studentId) throws DaoException {
        List<ExamResult> allMarks = getExamMarks();
        List<ExamResult> studentResult = new ArrayList<>();
        for (ExamResult examResult : allMarks) {
            if (examResult.getUserID() == studentId) {
                studentResult.add(examResult);
            }
        }
        return studentResult.size();
    }

    @Override
    public void deleteMark(int userId, int subjectId) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_DELETE_MARK);
            statement.setInt(1, subjectId);
            statement.setInt(2, userId);
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during adding mark", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
