package com.distant.system.service;

import com.distant.system.dao.DAOFactory;

import com.distant.system.dao.DAOManager;
import com.distant.system.dao.UserDao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
import com.distant.system.service.exception.ServiceException;


public class UserService {
    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final UserDao userDao = daoManager.getUserDAO();

    public String findPosition(String login) throws ServiceException {
        try {
            return userDao.findPosition(login);
        } catch (DaoException e) {
            throw new ServiceException("Exception during find position", e);
        }
    }

    public boolean checkIfExist(String login) throws ServiceException {
        try {
            return userDao.checkIfExist(login);
        } catch (DaoException e) {
            throw new ServiceException("Exception during check", e);
        }

    }

    public boolean isAuthorized(String login, String password) throws ServiceException {
        try {
            return userDao.isAuthorized(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Exception during a", e);
        }
    }

    public void addStudent(User user) throws ServiceException {
        try {
            userDao.addStudent(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception for user service", e);
        }
    }

    public int findUserID(String login) throws ServiceException {
        try {
            return userDao.findUserID(login);
        } catch (DaoException e) {
            throw new ServiceException("Exception for user service", e);
        }
    }

    public String getNameSurname(String login) throws ServiceException {
        try {
            return userDao.getNameSurname(login);
        } catch (DaoException e) {
            throw new ServiceException("Exception for user service", e);
        }
    }

}
