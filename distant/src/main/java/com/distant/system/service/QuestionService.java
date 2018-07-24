package com.distant.system.service;

import com.distant.system.entity.Question;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions(String subject, String language) throws ServiceException, ValidationException;

    void add(Question question) throws ServiceException, ValidationException;

    Question find(int id) throws ServiceException;

    void update(Question question) throws ServiceException, ValidationException;

    List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws ServiceException;

    int allQuestions(int subjectId, int langId) throws ServiceException, ValidationException;

    void add(List<Question> questions) throws ServiceException, ValidationException;

    void deleteQuestion(int id) throws ServiceException;
}
