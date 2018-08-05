
package com.distant.system.dao.mysql.impl;


import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.connection.ConnectionPool;
import com.distant.system.dao.connection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.AbstractDAO;
import com.distant.system.dao.util.DaoUtil;
import com.distant.system.entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InMemorySubjectDao extends AbstractDAO implements SubjectDao {


    private static final String ID = "id";
    private static final String SUBJECT = "subject";

    private static final Logger logger = LogManager.getLogger(InMemorySubjectDao.class);

    @Override
    public List<Subject> getAllsubjects() throws DaoException {
        List<Subject> subjects = new ArrayList<Subject>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_SUBJECTS);
            rs = statement.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(rs.getInt(ID));
                subject.setSubject(rs.getString(SUBJECT));
                subjects.add(subject);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting subjects from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                logger.error("SQL exception", e);
            }
        }
        return subjects;

    }

    @Override
    public List<Subject> getStudentAvailableSubjects(int studentId) throws DaoException {
        List<Subject> subjects = new ArrayList<Subject>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_AVAILABLE_SUBJECTS);
            statement.setInt(1, studentId);
            rs = statement.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(rs.getInt(ID));
                subject.setSubject(rs.getString(SUBJECT));
                subjects.add(subject);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting subjects from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                logger.error("SQL exception", e);
            }
        }

        return subjects;
    }


    @Override
    public int getSizeStudentAvailableSubjects(int studentId) throws DaoException {
        List<Subject> avalaibleSubjects = getStudentAvailableSubjects(studentId);
        return avalaibleSubjects.size();
    }

    @Override
    public List<Subject> numberOfStudentSubjects(int studentId, int offset, int records) throws DaoException {
        List<Subject> allSubjects = getStudentAvailableSubjects(studentId);
        return DaoUtil.numberOfStrings(allSubjects, offset, records);
    }

    @Override
    public List<Subject> numberOfAllSubjects(int offset, int records) throws DaoException {
        List<Subject> allSubject = getAllsubjects();
        return DaoUtil.numberOfStrings(allSubject, offset, records);
    }

    @Override
    public int getSizeAllSubjects() throws DaoException {
        List<Subject> allSubject = getAllsubjects();
        return allSubject.size();
    }

    @Override
    public void deleteSubject(int subjectId) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_DELETE_SUBJECT);
            statement.setInt(1, subjectId);
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during delete subject", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                logger.error("SQL exception", e);
            }
        }
    }

    @Override
    public void updateSubject(int subjectId, String value) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_SUBJECT);
            statement.setString(1, value);
            statement.setInt(2, subjectId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during updating subject", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                logger.error("SQL exception", e);
            }
        }
    }

    @Override
    public void addSubject(String subject) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_ADD_SUBJECT);
            statement.setString(1, subject);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during updating subject", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                logger.error("SQL exception", e);
            }
        }
    }

    @Override
    public String getSubjectById(int id) throws DaoException {
        String subject = "";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_SUBJECT_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                subject = rs.getString("subject");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting subjects from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                logger.error("SQL exception", e);
            }
        }
        return subject;
    }
}
