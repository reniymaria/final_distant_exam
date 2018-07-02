package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;

/**
 * Defines the methods for DAO factories
 */
public interface DAOManager {

    UserDao getUserDAO();

    MarkDao getMarkDAO();

    QuestionDao getQuestionDao();

    SubjectDao getSubjectDao();

    LanguageDao getLanguageDao();

    void startTransaction() throws DaoException;

    void setTransactionIsolation(int level) throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;

    void close() throws DaoException;

}