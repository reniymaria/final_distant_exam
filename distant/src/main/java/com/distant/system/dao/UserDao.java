
package com.distant.system.dao;


import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;

import java.util.List;

public interface UserDao {
    String SQL_ADD_STUDENT = "INSERT INTO users (login, password, name, surname, role) VALUES (?, ?, ?, ?, 'student')";
    String SQL_FIND_ALL_USERS = "SELECT * FROM users";

    List<User> allUsers() throws DaoException;

    String findPosition(String login) throws DaoException;

    void addStudent(User user) throws DaoException;

    boolean isAuthorized(String login, String password) throws DaoException;

    boolean checkIfExist(String login) throws DaoException;

    int findUserID(String login) throws DaoException;

    String getNameSurname(String login) throws DaoException;

}
