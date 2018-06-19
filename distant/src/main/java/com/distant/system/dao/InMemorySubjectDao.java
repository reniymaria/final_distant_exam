
package com.distant.system.dao;


import com.distant.system.db.ConnectionManager;
import com.distant.system.entity.Subject;

import java.sql.*;
import java.util.ArrayList;

public class InMemorySubjectDao implements SubjectDao {
    public ArrayList<Subject> getAllsubjects() {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SUBJECTS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectID(rs.getInt("id"));
                subject.setSubject(rs.getString("subject"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subjects;

    }

    public int getSubjectId(String subject)  {
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



    public void addSubjectForStudent(int studentId, String subject)  {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_SUBJECT);
            statement.setString(1, subject);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
