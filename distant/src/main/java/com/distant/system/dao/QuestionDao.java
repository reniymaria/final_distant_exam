package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * The Interface QuestionDao
 *
 * defines methods for working with questions.
 */

public interface QuestionDao {
    String SQL_FIND_ALL_QUESTIONS = "SELECT * FROM questions WHERE subjects_id =(SELECT id FROM subjects WHERE subject =?) AND languages_id =(SELECT id FROM languages WHERE language =?)";
    String SQL_FIND_QUESTION_BY_ID = "SELECT * FROM questions WHERE subjects_id = ? AND languages_id = ?";
    String SQL_ADD_QUESTION = "INSERT INTO questions (question, answer1, answer2, answer3, answer, subjects_id, languages_id) VALUES (?,?,?,?,?,?,?)";
    String SQL_FIND_BY_ID = "SELECT * FROM questions WHERE id = ?";
    String SQL_UPDATE_QUESTION = "UPDATE questions SET question=? WHERE id=?";
    String SQL_UPDATE_ANSWER1 = "UPDATE questions SET answer1=? WHERE id=?";
    String SQL_UPDATE_ANSWER2 = "UPDATE questions SET answer2=? WHERE id=?";
    String SQL_UPDATE_ANSWER3 = "UPDATE questions SET answer3=? WHERE id=?";
    String SQL_UPDATE_ANSWER = "UPDATE questions SET answer=? WHERE id=?";
    String SQL_DELETE_QUESTION =  "DELETE FROM questions WHERE id=?";
    /**
     * @param subject name of subject
     *
     * @param language String of language
     *
     * @return list of subjects that student can see
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */
    List<Question> getQuestions(String subject, String language) throws DaoException;
    /**
     * @param subjectId int id of subject
     *
     * @param langId id of language for question
     *
     * @return list of subjects that student can see
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */
    List<Question> getQuestionsById(int subjectId, int langId) throws DaoException;
    /**
     * @param question that will be added
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */
    void add(Question question) throws DaoException;
    /**
     * @param questions list of questions
     *
     * @throws DaoException
     *             the exception during getting data from DB
     */
    void add(List<Question> questions) throws DaoException;
    /**
     * @param id int id of question
     *
     * @return question
     *
     * @throws DaoException
     *            the exception during getting data from DB
     */
    Question find(int id) throws DaoException;
    /**
     * @param question that should be updated
     *
     * @throws DaoException
     *             the exception during getting data from DB
     */
    void update(Question question) throws DaoException;
    /**
     * @param subjectId int id of subject
     *
     * @param langId language for subject
     *
     * @param offset set of records
     *
     * @param records number of records
     *
     * @return list of questions
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */
    List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws DaoException;
    /**
     * @param subjectId id of subject
     *
     * @param langId id of language for subject
     *
     * @return number of questions
     *
     * @throws DaoException
     *             the exception during getting data from DB
     */
    int allQuestions(int subjectId, int langId) throws DaoException;
    /**
     * @param id question id
     *
     * @throws DaoException
     *              the exception during getting data from DB
     */
    void deleteQuestion(int id) throws DaoException;
}
