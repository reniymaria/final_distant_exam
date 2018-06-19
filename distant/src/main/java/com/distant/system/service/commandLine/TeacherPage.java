/*
package com.distant.system.service.commandLine;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.UserDao;
import com.distant.system.model.Question;
import com.distant.system.model.User;

import java.util.Scanner;

public class TeacherPage {

    private TeacherFunctions teacherFun;


    public TeacherPage(UserDao teacherDao, QuestionDao questionDao) {
        teacherFun = new TeacherFunctions(teacherDao, questionDao);
    }

    public void logInAsTeacher()  {
        String name = "";
        String surname = "";
        String password = "";

        System.out.println("Hi teacher");
        System.out.println("Please enter your name");
        Scanner in = new Scanner(System.in);
        try {
            name = in.nextLine();
            System.out.println("Please enter your surname");
            surname = in.nextLine();
            System.out.println("Please enter your password");
            password = in.nextLine();
        } catch (Exception e) {
            System.out.println("Incorrect value was entered. Let's try again :)");
            logInAsTeacher();
        }
        User teacher = new User(name, surname, password, "teacher");
        if (teacherFun.logInAsTeacher(teacher)) {
            teacherOptions(teacher);
        } else {
            System.out.println("No users with such credentials found. Please try again");
            logInAsTeacher();
        }
    }


    private void teacherOptions(User teacher) {
        int decision = 0;
        System.out.println("Hi " + teacher.getName() + " " + teacher.getSurname());
        System.out.println("Would you like to add question?");
        Scanner in = new Scanner(System.in);
        System.out.println("If 'yes' enter '1' if 'no' enter '2'");
        try {
            decision = in.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect value was entered. Let's try again :)");
            teacherOptions(teacher);
        }
        if (decision == 1) {
            addQuestion(teacher);
        } else if (decision == 2) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Incorrect value was entered. Let's try again :)");
            teacherOptions(teacher);
        }
    }

    private void addQuestion(User teacher) {
        String question = "";
        String answer1 = "";
        String answer2 = "";
        String answer3 = "";
        int correctAnswer = 0;
        String subject = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter question: ");
        try {
            question = in.nextLine();
            System.out.println("Please enter answer1: ");
            answer1 = in.nextLine();
            System.out.println("Please enter answer2: ");
            answer2 = in.nextLine();
            System.out.println("Please enter answer3: ");
            answer3 = in.nextLine();
            System.out.println("Please enter the NUMBER of the correct answer: ");
            correctAnswer = Integer.parseInt(in.nextLine());
            System.out.println("Please enter subject: ");
            subject = in.nextLine();
        } catch (Exception e) {
            System.out.println("Incorrect value was entered. Let's try again :)");
            addQuestion(teacher);
        }
        Question newQuestion = new Question(question, answer1, answer2, answer3, correctAnswer, subject);
        teacherFun.addQuestion(newQuestion);
        System.out.println("Your question successfully added");
        teacherOptions(teacher);
    }


}
*/
