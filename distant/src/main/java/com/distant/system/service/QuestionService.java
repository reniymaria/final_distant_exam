package com.distant.system.service;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.service.util.Validation;

import java.util.List;

public class QuestionService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final QuestionDao questionDao = daoManager.getQuestionDao();

    private static final String CON_LIST_QUESTION_ERROR = "con.list.question.error";
    private static final String CON_FIELD_EMPTY = "con.field.empty";

    public List<Question> getQuestions(String subject, String language) throws ServiceException, ValidationException {
        try {
            List<Question> questions = questionDao.getQuestions(subject, language);
            if (questions.size() < 5) {
                throw new ValidationException(CON_LIST_QUESTION_ERROR);
            } else {
                return questions;
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting list of questions", e);
        }
    }

    public void add(Question question) throws ServiceException, ValidationException {
        try {
            if (!Validation.isQuestionDataValid(question)) {
                throw new ValidationException(CON_FIELD_EMPTY);
            }
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

    public void update(Question question) throws ServiceException, ValidationException {
        try {
            if (!Validation.isQuestionDataValid(question)) {
                throw new ValidationException(CON_FIELD_EMPTY);
            } else {
                questionDao.update(question);
            }
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
