package com.distant.system.service.daoservice;

import com.distant.system.dao.UserDao;

import com.distant.system.entity.User;
import com.distant.system.factory.Factory;
import com.distant.system.factory.FactoryImpl;


public class UserService {
    private Factory factory = new FactoryImpl();
    private UserDao userDao = factory.createStudentDao();


    public String findPosition(String login) {
        return userDao.findPosition(login);
    }

    public boolean checkIfExist(String login) {
        return userDao.checkIfExist(login);
    }

    public boolean isAuthorized(String login, String password) {
        return userDao.isAuthorized(login, password);
    }

    public void addStudent(User user) {
        userDao.addStudent(user);
    }

}
