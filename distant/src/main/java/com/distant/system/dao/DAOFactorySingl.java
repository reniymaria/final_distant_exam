package com.distant.system.dao;

import com.distant.system.dao.mysql.impl.InMemoryMarkDao;
import com.distant.system.dao.mysql.impl.InMemoryQuestionDao;
import com.distant.system.dao.mysql.impl.InMemorySubjectDao;
import com.distant.system.dao.mysql.impl.InMemoryUserDao;

public class DAOFactorySingl {

    private static DAOFactorySingl instance;

    private static UserDao userDao;
    private static MarkDao markDao;
    private static QuestionDao questionDao;
    private static SubjectDao subjectDao;


    private DAOFactorySingl() {
    }

    public static DAOFactorySingl getInstance() {
        if (instance == null) {
            instance = new DAOFactorySingl();
        }
        return instance;
    }


    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new InMemoryUserDao();
        }
        return userDao;
    }

    public MarkDao getMarkDao() {
        if (markDao == null) {
            markDao = new InMemoryMarkDao();
        }
        return markDao;
    }

    public QuestionDao getQuestionDao() {
        if (questionDao == null) {
            questionDao = new InMemoryQuestionDao();
        }
        return questionDao;
    }

    public SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            subjectDao = new InMemorySubjectDao();
        }
        return subjectDao;
    }
}
