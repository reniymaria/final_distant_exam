package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;

import com.distant.system.dao.DAOManager;
import com.distant.system.dao.UserDao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;


public class UserService {
    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final UserDao userDao = daoManager.getUserDAO();

    public String findPosition(String login) throws DaoException {
        return userDao.findPosition(login);
    }

    public boolean checkIfExist(String login) throws DaoException {
        return userDao.checkIfExist(login);
    }

    public boolean isAuthorized(String login, String password) throws DaoException {
        return userDao.isAuthorized(login, password);
    }

    public void addStudent(User user) throws DaoException {
        userDao.addStudent(user);
    }

    public int findUserID(String login) throws DaoException {
        return userDao.findUserID(login);
    }

    public String getNameSurname(String login) throws DaoException {
        return userDao.getNameSurname(login);
    }

}
