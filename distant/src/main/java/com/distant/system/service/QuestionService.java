package com.distant.system.service;

import com.distant.system.entity.Question;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

import java.util.List;

/**
 * The Interface QuestionService
 *
 * defines methods for working with questions.
 */
public interface QuestionService {

    /**
     * @param subject  name of subject
     * @param language String of language
     * @return list of subjects that student can see
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception to validate Questions
     */
    List<Question> getQuestions(String subject, String language) throws ServiceException, ValidationException;

    /**
     * @param question that will be added
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception to validate question
     */
    void add(Question question) throws ServiceException, ValidationException;

    /**
     * @param id int id of question
     * @return question
     * @throws ServiceException the exception during getting data from DB
     */
    Question find(int id) throws ServiceException;

    /**
     * @param question that should be updated
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception to validate question
     */
    void update(Question question) throws ServiceException, ValidationException;

    /**
     * @param subjectId int id of subject
     * @param langId    language for subject
     * @param offset    set of records
     * @param records   number of records
     * @return list of questions
     * @throws ServiceException the exception during getting data from DB
     */
    List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws ServiceException;

    /**
     * @param subjectId id of subject
     * @param langId    id of language for subject
     * @return number of questions
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception
     */
    int allQuestions(int subjectId, int langId) throws ServiceException, ValidationException;

    /**
     * @param questions list of questions
     * @throws ServiceException    the exception during getting data from DB
     * @throws ValidationException the exception during validating questions
     */
    void add(List<Question> questions) throws ServiceException, ValidationException;

    /**
     * @param id question id
     * @throws ServiceException the exception during getting data from DB
     */
    void deleteQuestion(int id) throws ServiceException;
}
