
package com.distant.system.dao.mysql.impl;


import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.AbstractDAO;
import com.distant.system.entity.Subject;

import java.sql.*;
import java.util.ArrayList;

public class InMemorySubjectDao extends AbstractDAO implements SubjectDao {



    @Override
    public ArrayList<Subject> getAllsubjects() throws DaoException {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_SUBJECTS);
            rs = statement.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(rs.getInt("id"));
                subject.setSubject(rs.getString("subject"));
                subjects.add(subject);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting subjects from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subjects;

    }

    @Override
    public int getSubjectId(String subject) throws DaoException   {
        int subjectId = 0;
        ArrayList<Subject> subjects = getAllsubjects();
        for (Subject subjectList : subjects) {
            if(subjectList.getSubject().equals(subject)){
                subjectId = subjectList.getSubjectID();
                break;
            }
        }
        return subjectId;
    }


    @Override
    public void addSubjectForStudent(int studentId, String subject) throws DaoException  {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_ADD_SUBJECT);
            statement.setString(1, subject);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during adding subject.", e);
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
