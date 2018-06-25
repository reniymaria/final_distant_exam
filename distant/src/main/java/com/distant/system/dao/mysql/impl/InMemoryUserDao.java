
package com.distant.system.dao.mysql.impl;


import com.distant.system.dao.UserDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.AbstractDAO;
import com.distant.system.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InMemoryUserDao extends AbstractDAO implements UserDao {

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
                user.setUserID(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setRole(rs.getString("role"));
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
    public String findPosition(String login) throws DaoException  {
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
    public List<User> findUsers(String role) {
        return null;
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
    public boolean isAuthorized(String login, String password) throws DaoException{
        boolean isFound = false;
        List<User> users = allUsers();
        for(User allUser: users){
            if(allUser.getLogin().equalsIgnoreCase(login) && allUser.getPassword().equals(password)){
                isFound = true;
                break;
            }
        }

        return isFound;
    }

    @Override
    public boolean checkIfExist(String login) throws DaoException{
        List<User> users = allUsers();
        boolean isExist = false;
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login)) {
                isExist = true;
            }
        }
        return isExist;
    }


}
