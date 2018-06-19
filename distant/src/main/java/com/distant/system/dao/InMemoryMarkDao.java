package com.distant.system.dao;

import com.distant.system.db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InMemoryMarkDao implements MarkDao {

    public void addMark(int mark, int studentId, int subjectId) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_MARK);
            statement.setInt(1, mark);
            statement.setInt(2, studentId);
            statement.setInt(3, subjectId);
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
