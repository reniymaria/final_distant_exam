package com.distant.system.dao.mysql;

import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Class AbstractDAO is used as superclass for another DAO classes working
 * with a data base.
 */
public class AbstractDAO {

    private Connection connection;

    /**
     * Instantiates a new abstract DAO for operations.
     */
    public AbstractDAO() {
    }

    public Connection getConnection() throws ConnectionPoolException {
        if (connection == null) {
            return ConnectionPool.getInstance().getConnection();
        } else {
            return connection;
        }
    }

    public void closeMainConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}

