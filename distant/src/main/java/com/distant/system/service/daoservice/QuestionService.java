package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOFactorySingl;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.factory.Factory;
import com.distant.system.factory.FactoryImpl;
import com.distant.system.entity.Question;

import java.util.List;

public class QuestionService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final QuestionDao questionDao = daoManager.getQuestionDao();

    public List<Question> getQuestions(String subject, String language) throws DaoException {
        return questionDao.getQuestions(subject, language);
    }

    public void add(Question question) throws DaoException {
        questionDao.add(question);
    }

    public Question find(int id) throws DaoException {
        return questionDao.find(id);
    }

    public void update(Question question) throws DaoException {
        questionDao.update(question);
    }

    public List<Question> numberOfQuestions(String subject, String language, int offset, int records) throws DaoException {
        return questionDao.numberOfQuestions(subject, language, offset, records);
    }

    public int allQuestions(String subject, String language) throws DaoException {
        return questionDao.allQuestions(subject,language);
    }
}
