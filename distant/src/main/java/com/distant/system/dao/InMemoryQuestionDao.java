package com.distant.system.dao;


import com.distant.system.db.ConnectionManager;
import com.distant.system.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InMemoryQuestionDao implements QuestionDao {

    public List<Question> getQuestions(String subject, String language) {
        List<Question> questions = new ArrayList<Question>();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_QUESTIONS);
            statement.setString(1, subject);
            statement.setString(2, language);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setQuestionId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setAnswer1(rs.getString("answer1"));
                question.setAnswer2(rs.getString("answer2"));
                question.setAnswer3(rs.getString("answer3"));
                question.setCorrectAnswer(rs.getInt("answer"));
                question.setSubjectId(rs.getInt("subjects_id"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

    public void add(Question question) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_QUESTION);
            statement.setString(1, question.getQuestion());
            statement.setString(2, question.getAnswer1());
            statement.setString(3, question.getAnswer2());
            statement.setString(4, question.getAnswer3());
            statement.setInt(5, question.getCorrectAnswer());
            statement.setInt(6, question.getSubjectId());
            statement.setInt(7, question.getLanguageId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Question find(int id) {
        Question question = new Question();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                question.setQuestionId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setAnswer1(rs.getString("answer1"));
                question.setAnswer2(rs.getString("answer2"));
                question.setAnswer3(rs.getString("answer3"));
                question.setCorrectAnswer(rs.getInt("answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return question;
    }

    @Override
    public void update(Question question) {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUESTION);
            statement.setString(1, question.getQuestion());
            statement.setString(2, question.getAnswer1());
            statement.setString(3, question.getAnswer2());
            statement.setString(4, question.getAnswer3());
            statement.setInt(5, question.getCorrectAnswer());
            statement.setInt(6, question.getQuestionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Question> numberOfQuestions(String subject, String language, int offset, int records) {
        List<Question> allQuestions = getQuestions(subject, language);
        List<Question> result = new ArrayList<>();
        for (int i = 0; i < allQuestions.size(); i++) {
            if (offset == 0) {
                    if (i < records) {
                        result.add(allQuestions.get(i));

                }
            }
            else if (offset > 0) {
                    if (i > offset * records && i < (offset * records + records + 1)) {
                        result.add(allQuestions.get(i));
                    }

            }
        }
        return result;
    }

    @Override
    public int allQuestions(String subject, String language) {
        List<Question> allQuestions = getQuestions(subject, language);
        return allQuestions.size();

    }

}

