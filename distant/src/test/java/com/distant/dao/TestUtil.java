package com.distant.dao;


import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class TestUtil {

    private static final String DB_PROPERTIES_FILE = "db_test";

    private static final String RECREATE_DB_SQL_FILE = "distant_exam_test.sql";

    private TestUtil() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static void initializeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().initialize(DB_PROPERTIES_FILE);
    }

    public static void initializeDB() throws ConnectionPoolException, SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ScriptRunner scriptRunner = new ScriptRunner(connection);

            ClassLoader classLoader = UserDAOTest.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(RECREATE_DB_SQL_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            scriptRunner.runScript(reader);
        } finally {
            connection.close();
        }

    }

}
