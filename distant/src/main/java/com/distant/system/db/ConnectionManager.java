package com.distant.system.db;

import java.sql.*;

public class ConnectionManager {


    String URL = "jdbc:mysql://localhost:3306/distant_exam";
    String USER = "root";
    String PASSWORD = "root";

    private Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
