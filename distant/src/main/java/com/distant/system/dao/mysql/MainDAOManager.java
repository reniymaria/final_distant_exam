package com.distant.system.dao.mysql;

import com.distant.system.dao.*;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.impl.*;

import java.sql.Connection;

/**
 * It is a factory for creating DAO implementation objects as singletons.
 */

public class MainDAOManager implements DAOManager {


    private static final UserDao userDao = new InMemoryUserDao();
    private static final MarkDao markDao = new InMemoryMarkDao();
    private static final QuestionDao questionDao = new InMemoryQuestionDao();
    private static final SubjectDao subjectDao = new InMemorySubjectDao();
    private static final LanguageDao languageDao = new InMemoryLanguageDao();

    @Override
    public UserDao getUserDAO() {
        return userDao;
    }

    @Override
    public MarkDao getMarkDAO() {
        return markDao;
    }

    @Override
    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    @Override
    public SubjectDao getSubjectDao() {
        return subjectDao;
    }

    @Override
    public LanguageDao getLanguageDao() {
        return languageDao;
    }

    @Override
    public void close() throws DaoException {
        throw new UnsupportedOperationException();
    }

}