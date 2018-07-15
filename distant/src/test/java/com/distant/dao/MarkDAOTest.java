package com.distant.dao;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.MarkDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class MarkDAOTest {

    private static final MarkDao markDao = DAOFactory.getFactory().getMainDAOManager().getMarkDAO();

    private static final String SQL_FIND_MARK = "SELECT mark FROM marks WHERE subjects_id =? and users_id= ?";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        TestUtil.initializeConnectionPool();

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ConnectionPool.getInstance().destroy();
    }

    @Before
    public void setUp() throws Exception {
        TestUtil.initializeDB();
    }


    @Test
    public void addMark() throws DaoException, SQLException, ConnectionPoolException {
        int mark = 5;
        int subjectId = 1;
        int userId = 5;
        markDao.addMark(mark, userId, subjectId);

        assertEquals(mark, getMark(userId, subjectId));
    }

    private int getMark(int userId, int subjectId) throws SQLException, ConnectionPoolException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int mark = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_FIND_MARK);
            statement.setInt(1, subjectId);
            statement.setInt(2, userId);
            rs = statement.executeQuery();
            rs.next();
            mark = rs.getInt("mark");
            return mark;
        } finally {
            ConnectionPool.getInstance().closeDBResources(connection, statement, rs);
        }
    }

    @Test
    public void allStudentMarks() throws DaoException {
        int userId = 5;
        int expectedResult = 2;

        assertEquals(markDao.allStudentMarks(userId), expectedResult);
    }

    @Test
    public void deleteMark() throws DaoException {
        int userId = 5;
        int subjectId = 2;
        markDao.deleteMark(userId, subjectId);
        int actualResult = markDao.allStudentMarks(5);
        assertEquals(actualResult, 1);
    }


}
