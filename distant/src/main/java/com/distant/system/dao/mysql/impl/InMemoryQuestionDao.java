package com.distant.system.dao.mysql.impl;


import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.mysql.AbstractDAO;
import com.distant.system.dao.util.DaoUtil;
import com.distant.system.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InMemoryQuestionDao extends AbstractDAO implements QuestionDao {

    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String ANSWER_1 = "answer1";
    private static final String ANSWER_2 = "answer2";
    private static final String ANSWER_3 = "answer3";
    private static final String ANSWER = "answer";
    private static final String SUBJECTS_ID = "subjects_id";
    private static final String LANGUAGES_ID = "languages_id";

    @Override
    public List<Question> getQuestions(String subject, String language) throws DaoException {
        List<Question> questions = new ArrayList<Question>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_QUESTIONS);
            statement.setString(1, subject);
            statement.setString(2, language);
            rs = statement.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt(ID));
                question.setQuestion(rs.getString(QUESTION));
                question.setAnswer1(rs.getString(ANSWER_1));
                question.setAnswer2(rs.getString(ANSWER_2));
                question.setAnswer3(rs.getString(ANSWER_3));
                question.setCorrectAnswer(rs.getInt(ANSWER));
                question.setSubjectId(rs.getInt(SUBJECTS_ID));
                question.setLanguageId(rs.getInt(LANGUAGES_ID));
                questions.add(question);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting questions from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

    @Override
    public List<Question> getQuestionsById(int subjectId, int langId) throws DaoException {
        List<Question> questions = new ArrayList<Question>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_QUESTION_BY_ID);
            statement.setInt(1, subjectId);
            statement.setInt(2, langId);
            rs = statement.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt(ID));
                question.setQuestion(rs.getString(QUESTION));
                question.setAnswer1(rs.getString(ANSWER_1));
                question.setAnswer2(rs.getString(ANSWER_2));
                question.setAnswer3(rs.getString(ANSWER_3));
                question.setCorrectAnswer(rs.getInt(ANSWER));
                question.setSubjectId(rs.getInt(SUBJECTS_ID));
                question.setLanguageId(rs.getInt(LANGUAGES_ID));
                questions.add(question);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during getting questions from DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

    @Override
    public void add(Question question) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_ADD_QUESTION);
            statement.setString(1, question.getQuestion());
            statement.setString(2, question.getAnswer1());
            statement.setString(3, question.getAnswer2());
            statement.setString(4, question.getAnswer3());
            statement.setInt(5, question.getCorrectAnswer());
            statement.setInt(6, question.getSubjectId());
            statement.setInt(7, question.getLanguageId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during adding question to DB.", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(List<Question> questions) throws DaoException {
        for (Question question : questions) {
            add(question);
        }
    }

    @Override
    public Question find(int id) throws DaoException {
        Question question = new Question();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                question.setQuestionId(rs.getInt(ID));
                question.setQuestion(rs.getString(QUESTION));
                question.setAnswer1(rs.getString(ANSWER_1));
                question.setAnswer2(rs.getString(ANSWER_2));
                question.setAnswer3(rs.getString(ANSWER_3));
                question.setCorrectAnswer(rs.getInt(ANSWER));
                question.setLanguageId(rs.getInt(LANGUAGES_ID));
                question.setSubjectId(rs.getInt(SUBJECTS_ID));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during finding question", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(rs, statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return question;
    }

    @Override
    public void update(Question question) throws DaoException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_QUESTION);
            statement.setString(1, question.getQuestion());
            statement.setInt(2, question.getQuestionId());
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL_UPDATE_ANSWER1);
            statement.setString(1, question.getAnswer1());
            statement.setInt(2, question.getQuestionId());
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL_UPDATE_ANSWER2);
            statement.setString(1, question.getAnswer2());
            statement.setInt(2, question.getQuestionId());
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL_UPDATE_ANSWER3);
            statement.setString(1, question.getAnswer3());
            statement.setInt(2, question.getQuestionId());
            statement.executeUpdate();

            statement = connection.prepareStatement(SQL_UPDATE_ANSWER);
            statement.setInt(1, question.getCorrectAnswer());
            statement.setInt(2, question.getQuestionId());
            statement.executeUpdate();


        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during updating question", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Question> numberOfQuestions(int subjectId, int langId, int offset, int records) throws DaoException {
        List<Question> allQuestions = getQuestionsById(subjectId, langId);
        return DaoUtil.numberOfStrings(allQuestions, offset, records);
    }

    @Override
    public int allQuestions(int subjectId, int langId) throws DaoException {
        List<Question> allQuestions = getQuestionsById(subjectId, langId);
        return allQuestions.size();

    }

    @Override
    public void deleteQuestion(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SQL_DELETE_QUESTION);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception during delete question", e);
        } finally {
            try {
                closeMainConnection(connection);
                ConnectionPool.getInstance().closeDBResources(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

