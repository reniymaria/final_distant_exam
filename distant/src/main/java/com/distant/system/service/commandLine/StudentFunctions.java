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
import java.util.Collections;

public class StudentFunctions {
    private QuestionDao questionDao;
    private SubjectDao subjectDao;
    private UserDao userDao;
    private MarkDao markDao;


    public StudentFunctions(QuestionDao questionDao, SubjectDao subjectDao, UserDao userDao, MarkDao markDao) {
        this.questionDao = questionDao;
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.markDao = markDao;
    }


    public boolean checkIfStudentLoggedIn(String name, String surname, String password) {

        return userDao.logInAsStudent(name, surname, password);
    }


    public void addStudent(User student)  {
        userDao.addStudent(student);
    }



    public int getStudentId(User user) {
        int studentId = 0;
        ArrayList<User> users = userDao.allStudents();
        for (User stud : users) {
            if (stud.getName().equals(user.getName())
                    && stud.getSurname().equals(user.getSurname())
                    && stud.getPassword().equals(user.getPassword())) {
                studentId = stud.getUserID();
            }
        }
        return studentId;
    }


    public void addSubject(User user) {
        int studentId = getStudentId(user);
        ArrayList<Subject> subjects = subjectDao.getAllsubjects();
        for (Subject subj : subjects) {
            subjectDao.addSubjectForStudent(studentId, subj.getSubject());
        }
    }


    public ArrayList<Subject> getAllsubjects()  {
        return subjectDao.getAllsubjects();
    }


    public int getSubjectId(String subject)  {
        return subjectDao.getSubjectId(subject);
    }


    public ArrayList<Question> getAllQuestions(String subject) {
        return questionDao.getQuestions(subject);
    }


    public void addMark(int mark, int studentId, int subjectId)  {
        markDao.addMark(mark, studentId, subjectId);
    }

    public ArrayList<Question> getRandomFive(ArrayList<Question> questions) {
        ArrayList<Question> fiveQuestions = new ArrayList<Question>();
        Collections.shuffle(questions);
        for (Question question : questions) {
            fiveQuestions.add(question);
            if (fiveQuestions.size() < 5) {
                continue;
            } else break;
        }
        return fiveQuestions;
    }

}
*/
