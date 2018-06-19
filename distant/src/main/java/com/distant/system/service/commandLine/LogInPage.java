/*
package com.distant.system.service.commandLine;

import com.distant.system.dao.MarkDao;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.UserDao;

import java.util.Scanner;

public class LogInPage {

    private QuestionDao questionDao;
    private SubjectDao subjectDao;
    private UserDao userDao;
    private MarkDao markDao;

    public LogInPage(QuestionDao questionDao, SubjectDao subjectDao, UserDao userDao, MarkDao markDao) {
        this.questionDao = questionDao;
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.markDao = markDao;
    }

    public void chooseWhoToLogIn()  {
        int logIn = 0;
        System.out.println("Hello guest! Would you like to log in as Student or Teacher?");
        System.out.println("Please enter '1' for Student and '2' for Teacher");
        Scanner in = new Scanner(System.in);
        try {
            logIn = in.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect value was entered. Let's try again :)");
            chooseWhoToLogIn();
        }
        if (logIn == 1) {
            StudentPage studentPage = new StudentPage(questionDao, subjectDao, userDao, markDao);
            studentPage.chooseRegisterLogIn();
        } else if (logIn == 2) {
            TeacherPage teacherPage = new TeacherPage(userDao, questionDao);
            teacherPage.logInAsTeacher();
        } else {
            System.out.println("Incorrect value was entered. Let's try again :)");
            chooseWhoToLogIn();
        }
    }

    public String findPosition(String name, String password)  {
       return userDao.findPosition(name,password);
    }

    public boolean checkIfExist(String name, String password)  {
        return userDao.checkIfExist(name, password);
    }


}


*/
