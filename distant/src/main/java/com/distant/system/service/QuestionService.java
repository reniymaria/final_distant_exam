package com.distant.system.service;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.exception.ServiceException;

import java.util.List;

public class QuestionService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final QuestionDao questionDao = daoManager.getQuestionDao();

    public List<Question> getQuestions(String subject, String language) throws ServiceException {
        try {
            return questionDao.getQuestions(subject, language);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting list of questions", e);
        }
    }

    public void add(Question question) throws ServiceException {
        try {
            questionDao.add(question);
        } catch (DaoException e) {
            throw new ServiceException("Exception during adding questions", e);
        }
    }

    public Question find(int id) throws ServiceException {
        try {
            return questionDao.find(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception during finding questions", e);
        }

    }

    public void update(Question question) throws ServiceException {
        try {
            questionDao.update(question);
        } catch (DaoException e) {
            throw new ServiceException("Exception during updating question", e);
        }
    }

    public List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws ServiceException {
        try {
            return questionDao.numberOfQuestions(subjectId, langId, offset, records);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting list of questions", e);
        }
    }

    public int allQuestions(int subjectId, int langId) throws ServiceException {
        try {
            return questionDao.allQuestions(subjectId, langId);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting questions", e);
        }
    }

    public void add(List<Question> questions) throws ServiceException {
        try {
            questionDao.add(questions);
        } catch (DaoException e) {
            throw new ServiceException("Exception during adding list of questions", e);
        }
    }

    public void deleteQuestion(int id) throws ServiceException {
        try {
            questionDao.deleteQuestion(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception during deleting question", e);
        }
    }
}
