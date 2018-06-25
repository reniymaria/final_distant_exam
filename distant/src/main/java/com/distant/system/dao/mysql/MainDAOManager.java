package com.distant.system.dao.mysql;

import com.distant.system.dao.*;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.impl.InMemoryMarkDao;
import com.distant.system.dao.mysql.impl.InMemoryQuestionDao;
import com.distant.system.dao.mysql.impl.InMemorySubjectDao;
import com.distant.system.dao.mysql.impl.InMemoryUserDao;

import java.sql.Connection;

/**
 * It is a factory for creating DAO implementation objects as singletons.
 * Doesn't support transactional operations.
 */

public class MainDAOManager implements DAOManager {


    private static final UserDao userDao = new InMemoryUserDao();
    private static final MarkDao markDao = new InMemoryMarkDao();
    private static final QuestionDao questionDao = new InMemoryQuestionDao();
    private static final SubjectDao subjectDao = new InMemorySubjectDao();

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
    public void startTransaction() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTransactionIsolation(int level) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void commit() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rollback() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws DaoException {
        throw new UnsupportedOperationException();
    }

}