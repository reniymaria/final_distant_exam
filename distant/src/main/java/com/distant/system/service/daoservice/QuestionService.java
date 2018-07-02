package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.exception.DaoException;
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

    public List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws DaoException {
        return questionDao.numberOfQuestions(subjectId, langId, offset, records);
    }

    public int allQuestions(int subjectId, int langId) throws DaoException {
        return questionDao.allQuestions(subjectId, langId);
    }
}
