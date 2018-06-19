
package com.distant.system.dao;


import com.distant.system.db.ConnectionManager;
import com.distant.system.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InMemoryUserDao implements UserDao {

    public List<User> allUsers() {
        ArrayList<User> users = new ArrayList<User>();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet rs = statement.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }



    public String findPosition(String login)  {
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


    public void addStudent(User user)  {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_STUDENT);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
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

    @Override
    public boolean isAuthorized(String login, String password) {
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

    public boolean checkIfExist(String login){
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
