
package com.distant.system.dao;


import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;

import java.util.List;

/**
 * The Interface UserDAO
 *
 * defines methods for working with user.
 */
public interface UserDao {
    String SQL_ADD_STUDENT = "INSERT INTO users (login, password, name, surname, role) VALUES (?, ?, ?, ?, 'student')";
    String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    String SQL_FIND_USER = "SELECT id, login, name, surname, role FROM users WHERE login =? AND password =?";

    /**
     * @return List of users from DB
     * @throws DaoException the exception during getting user from DB
     */

    List<User> allUsers() throws DaoException;

    String findPosition(String login) throws DaoException;

    /**
     * @param user User user
     * @throws DaoException the exception during getting user from DB
     */

    void addStudent(User user) throws DaoException;

    /**
     * @param login    the login
     * @param password the password
     * @return true if user exists and false if user doesn't exist
     * @throws DaoException the exception during getting user from DB
     */

    boolean isAuthorized(String login, String password) throws DaoException;

    /**
     * @param login the login
     * @return true if user exists and false if user doesn't exist
     * @throws DaoException the exception during getting user from DB
     */

    boolean checkIfExist(String login) throws DaoException;

    /**
     * @param login the login
     * @return the name ans surname of user
     * @throws DaoException the exception during getting user from DB
     */

    String getNameSurname(String login) throws DaoException;

    /**
     * Returns the {@link User} who appropriates specified {@code login}
     * and {@code password}. Returns {@code null} if such user is not found.
     *
     * @param login    the login
     * @param password the password
     * @return the user who appropriates specified {@code login} and
     * {@code password}. Returns {@code null} if such user is not found.
     * @throws DaoException the exception during getting user from DB
     */

    User logIn(String login, String password) throws DaoException;

}
