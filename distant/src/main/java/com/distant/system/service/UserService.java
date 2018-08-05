package com.distant.system.service;

import com.distant.system.entity.User;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

/**
 * The Interface UserService
 *
 * defines methods for working with user.
 */
public interface UserService {

    /**
     * @param user User user
     * @throws ServiceException    the exception during getting user from DB
     * @throws ValidationException the exception if added user is incorrect
     */

    void addStudent(User user) throws ServiceException, ValidationException;

    /**
     * Returns the {@link User} who appropriates specified {@code login}
     * and {@code password}. Returns {@code null} if such user is not found.
     *
     * @param login    the login
     * @param password the password
     * @return the user who appropriates specified {@code login} and
     * {@code password}. Returns {@code null} if such user is not found.
     * @throws ServiceException    the exception during getting user from DB
     * @throws ValidationException the exception if login or password is incorrect
     */
    User logIn(String login, String password) throws ValidationException, ServiceException;
}
