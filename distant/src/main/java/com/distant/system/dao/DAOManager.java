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

    void close() throws DaoException;

}