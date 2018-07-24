package com.distant.system.service.impl;

import com.distant.system.service.UserService;
import com.distant.system.service.util.HashUtil;
import com.distant.system.dao.DAOFactory;

import com.distant.system.dao.DAOManager;
import com.distant.system.dao.UserDao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.service.util.Validation;


public class UserServiceImpl implements UserService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final UserDao userDao = daoManager.getUserDAO();

    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String CON_ERROR_LOGIN_PASSWORD = "con.error.login.password";
    private static final String CON_LOGINEXIST = "con.loginexist";

    @Override
    public void addStudent(User user) throws ServiceException, ValidationException {

        if (!Validation.isRegistrationDataValid(user)) {
            throw new ValidationException(CON_FIELD_EMPTY);
        }
        try {
            if (userDao.checkIfExist(user.getLogin())) {
                throw new ValidationException(CON_LOGINEXIST);
            } else {
                String hashPassword = HashUtil.md5Apache(user.getPassword());
                user.setPassword(hashPassword);
                userDao.addStudent(user);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception for user service", e);
        }
    }

    @Override
    public User logIn(String login, String password) throws ValidationException, ServiceException {
        User user;

        try {
            if (!Validation.isLoginDataValid(login, password)) {
                throw new ValidationException(CON_FIELD_EMPTY);
            }

            String hashPass = HashUtil.md5Apache(password);
            user = userDao.logIn(login, hashPass);
            if (user.getLogin() == null) {
                throw new ValidationException(CON_ERROR_LOGIN_PASSWORD);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception for user service", e);

        }
        return user;
    }

}
