
package com.distant.system.dao.mysql.impl;


import com.distant.system.dao.UserDao;
import com.distant.system.dao.connection.ConnectionPool;
import com.distant.system.dao.connection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.AbstractDAO;
import com.distant.system.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InMemoryUserDao extends AbstractDAO implements UserDao {

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    public static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";

    @Override
    public List<User> allUsers() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        ArrayList<User> users = new ArrayList<User>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt(ID));
                user.setLogin(rs.getString(LOGIN));
                user.setPassword(rs.getString(PASSWORD));
                user.setName(rs.getString(NAME));
                user.setSurname(rs.getString(SURNAME));
                user.setRole(rs.getString(ROLE));
                users.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting users from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }


    @Override
    public String findPosition(String login) throws DaoException {
        String position = "";
        List<User> users = allUsers();
        for (User usersList : users) {
            if (usersList.getLogin().equalsIgnoreCase(login)) {
                position = usersList.getRole();
                break;
            }
        }
        return position;
    }


    @Override
    public void addStudent(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_ADD_STUDENT);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during adding student to DB.", e);
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
    public boolean isAuthorized(String login, String password) throws DaoException {
        boolean isFound = false;
        List<User> users = allUsers();
        for (User allUser : users) {
            if (allUser.getLogin().equalsIgnoreCase(login) && allUser.getPassword().equals(password)) {
                isFound = true;
                break;
            }
        }

        return isFound;
    }

    @Override
    public boolean checkIfExist(String login) throws DaoException {
        List<User> users = allUsers();
        boolean isExist = false;
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                isExist = true;
            }
        }
        return isExist;
    }

    @Override
    public String getNameSurname(String login) throws DaoException {
        String nameAndSurname = "";
        List<User> users = allUsers();
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                nameAndSurname = user.getName() + " " + user.getSurname();
            }

        }
        return nameAndSurname;
    }

    @Override
    public User logIn(String login, String password) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        User user = new User();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            rs = statement.executeQuery();

            while (rs.next()) {
                user.setUserID(rs.getInt(ID));
                user.setLogin(rs.getString(LOGIN));
                user.setName(rs.getString(NAME));
                user.setSurname(rs.getString(SURNAME));
                user.setRole(rs.getString(ROLE));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting user from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }


}
