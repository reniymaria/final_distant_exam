/*
package com.distant.system.service.commandLine;

import com.distant.system.dao.MarkDao;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.UserDao;
import com.distant.system.model.Question;
import com.distant.system.model.Subject;
import com.distant.system.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentPage {

    private StudentFunctions studFunc;

    public StudentPage(QuestionDao questionDao, SubjectDao subjectDao, UserDao userDao, MarkDao markDao) {
        studFunc = new StudentFunctions(questionDao, subjectDao, userDao, markDao);
    }


    private void selectSubject(User user) {
        String subject = "";
        System.out.println("Please enter what subject you would like:");
        ArrayList<Subject> subjects = studFunc.getAllsubjects();
        System.out.println("List of subjects:");
        for (Subject subj : subjects) {
            System.out.println(subj);
        }
        Scanner in = new Scanner(System.in);
        try {
            subject = in.nextLine();
        } catch (Exception e) {
            System.out.println("Incorrect value was entered. Let's try again :)");
        }
        int subjectId = studFunc.getSubjectId(subject);
        ArrayList<Question> questions = studFunc.getAllQuestions(subject);
        if (questions.size() < 5) {
            System.out.println("You don't have enough questions for exam");
            System.out.println("Choose another exam");
            selectSubject(user);
        }
        ArrayList<Question> fiveQuestion = studFunc.getRandomFive(questions);
        int mark = answerQuestions(fiveQuestion);
        int studentId = studFunc.getStudentId(user);
        studFunc.addMark(mark, studentId, subjectId);
        System.out.println("Your mark is " + mark);
        System.out.println("Thank you and good buy!");

    }


    private int answerQuestions(ArrayList<Question> questions) {
        int studentAnswer = 0;
        int mark = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            System.out.println("Enter '1' if answer is: " + question.getAnswer1());
            System.out.println("Enter '2' if answer is: " + question.getAnswer2());
            System.out.println("Enter '3' if answer is: " + question.getAnswer3());
            Scanner in = new Scanner(System.in);
            try {
                studentAnswer = in.nextInt();
            } catch (Exception e) {
                System.out.println("Incorrect value was entered. Let's try again :)");
                answerQuestions(questions);
            }
            if (studentAnswer == question.getCorrectAnswer()) {
                mark++;
            }
        }
        return mark;
    }



}
*/
