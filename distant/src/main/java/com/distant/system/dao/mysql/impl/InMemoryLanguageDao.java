package com.distant.system.dao.mysql.impl;

import com.distant.system.dao.LanguageDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.AbstractDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InMemoryLanguageDao extends AbstractDAO implements LanguageDao {


    @Override
    public int findId(String language) throws DaoException {
        int languageID = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FINDID_BY_LANG);
            statement.setString(1, language);
            rs = statement.executeQuery();
            while (rs.next()) {
                languageID = rs.getInt("id");
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

        return languageID;
    }
}
