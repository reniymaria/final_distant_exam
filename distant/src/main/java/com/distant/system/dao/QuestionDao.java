package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;

import java.util.ArrayList;
import java.util.List;

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

    List<Question> getQuestions(String subject, String language) throws DaoException;

    List<Question> getQuestionsById(int subjectId, int langId) throws DaoException;

    void add(Question question) throws DaoException;

    void add(List<Question> questions) throws DaoException;

    Question find(int id) throws DaoException;

    void update(Question question) throws DaoException;

    List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws DaoException;

    int allQuestions(int subjectId, int langId) throws DaoException;

    void deleteQuestion(int id) throws DaoException;
}
