
package com.distant.system.dao;


import com.distant.system.entity.User;

import java.util.List;

public interface UserDao {
    String SQL_ADD_STUDENT = "INSERT INTO users (login, password, name, surname, role) VALUES (?, ?, ?, ?, 'student')";
    String SQL_FIND_ALL_USERS = "SELECT * FROM users";

    List<User> allUsers();

    String findPosition(String login);

    List<User> findUsers(String role);

    void addStudent(User user);

    boolean isAuthorized(String login, String password);

    boolean checkIfExist(String login);

}
