package com.distant.system.dao;

import com.distant.system.entity.Question;

import java.util.ArrayList;
import java.util.List;

public interface QuestionDao {
    String SQL_FIND_ALL_QUESTIONS = "SELECT * FROM questions WHERE subjects_id =(SELECT id FROM subjects WHERE subject =?) AND languages_id =(SELECT id FROM languages WHERE language =?)";
    String SQL_ADD_QUESTION = "INSERT INTO questions (question, answer1, answer2, answer3, answer, subjects_id, languages_id) VALUES (?,?,?,?,?,?)";
    String SQL_FIND_BY_ID = "SELECT * FROM questions WHERE id = ?";
    String SQL_UPDATE_QUESTION = "UPDATE questions SET question =? answer1=? answer2=? answer3=? answer=? WHERE id=?";

    List<Question> getQuestions(String subject, String language);

    void add(Question question);

    Question find(int id);

    void update(Question question);

    List<Question> numberOfQuestions(String subject, String language, int offset, int records);

    int allQuestions(String subject, String language);
}
