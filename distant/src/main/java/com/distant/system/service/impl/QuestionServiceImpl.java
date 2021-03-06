package com.distant.system.service.impl;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.QuestionService;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.service.util.Validation;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final QuestionDao questionDao = daoManager.getQuestionDao();

    private static final String CON_LIST_QUESTION_ERROR = "con.list.question.error";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String CON_LIST_EMPTY = "con.list.empty";
    private static final String CON_INCORRECT_DATA_UPLOADED = "con.incorrect.data.uploaded";

    @Override
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

    @Override
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

    @Override
    public Question find(int id) throws ServiceException {
        try {
            return questionDao.find(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception during finding questions", e);
        }

    }

    @Override
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

    @Override
    public List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws ServiceException {
        try {
            return questionDao.numberOfQuestions(subjectId, langId, offset, records);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting list of questions", e);
        }
    }

    @Override
    public int allQuestions(int subjectId, int langId) throws ServiceException, ValidationException {
        try {
            if (questionDao.allQuestions(subjectId, langId) == 0) {
                throw new ValidationException(CON_LIST_EMPTY);
            } else {
                return questionDao.allQuestions(subjectId, langId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting questions", e);
        }
    }

    @Override
    public void add(List<Question> questions) throws ServiceException, ValidationException {
        try {
            for (Question question : questions) {
                if (!Validation.isQuestionDataValid(question)) {
                    throw new ValidationException(CON_INCORRECT_DATA_UPLOADED);
                } else {
                    questionDao.add(questions);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during adding list of questions", e);
        }
    }

    @Override
    public void deleteQuestion(int id) throws ServiceException {
        try {
            questionDao.deleteQuestion(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception during deleting question", e);
        }
    }
}
