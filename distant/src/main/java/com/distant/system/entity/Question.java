package com.distant.system.entity;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {

    private int questionId;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private int correctAnswer;
    private int subjectId;
    private int languageId;


    public Question() {

    }

    public Question(String question, String answer1, String answer2, String answer3, int correctAnswer, int subjectId, int languageId) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswer = correctAnswer;
        this.subjectId = subjectId;
        this.languageId = languageId;
    }

    public Question(int questionId, String question, String answer1, String answer2, String answer3, int correctAnswer, int subjectId, int languageId) {
        this.questionId = questionId;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correctAnswer = correctAnswer;
        this.subjectId = subjectId;
        this.languageId = languageId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return questionId == question1.questionId &&
                correctAnswer == question1.correctAnswer &&
                subjectId == question1.subjectId &&
                languageId == question1.languageId &&
                Objects.equals(question, question1.question) &&
                Objects.equals(answer1, question1.answer1) &&
                Objects.equals(answer2, question1.answer2) &&
                Objects.equals(answer3, question1.answer3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(questionId, question, answer1, answer2, answer3, correctAnswer, subjectId, languageId);
    }
}
