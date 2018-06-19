package com.distant.system.service.daoservice;

import com.distant.system.dao.QuestionDao;
import com.distant.system.factory.Factory;
import com.distant.system.factory.FactoryImpl;
import com.distant.system.entity.Question;

import java.util.List;

public class QuestionService {

    private Factory factory = new FactoryImpl();
    private QuestionDao questionDao = factory.createQuestionDao();

    public List<Question> getQuestions(String subject, String language) {
        return questionDao.getQuestions(subject, language);
    }

    public void add(Question question) {
        questionDao.add(question);
    }

    public Question find(int id) {
        return questionDao.find(id);
    }

    public void update(Question question) {
        questionDao.update(question);
    }

    public List<Question> numberOfQuestions(String subject, String language, int offset, int records) {
        return questionDao.numberOfQuestions(subject, language, offset, records);
    }

    public int allQuestions(String subject, String language) {
        return questionDao.allQuestions(subject,language);
    }
}
