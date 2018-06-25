package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOFactorySingl;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.UserDao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
import com.distant.system.factory.Factory;
import com.distant.system.factory.FactoryImpl;


public class UserService {
    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final UserDao userDao = daoManager.getUserDAO();
    // private static final UserDao userDao = DAOFactorySingl.getInstance().getUserDao();

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

}
