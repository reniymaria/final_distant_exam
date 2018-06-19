package com.distant.system.controller;

import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.QuestionService;
import com.distant.system.service.daoservice.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addquestion")
public class AddQuestion extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        SubjectService subjectService = new SubjectService();

        String question = request.getParameter("question");
        if (question != null) {
            int correctAnswer = 1;
            if (request.getParameter("correctAnswer") != null) {
                correctAnswer = Integer.parseInt(request.getParameter("correctAnswer"));
            }
            String answer1 = request.getParameter("answer1");
            String answer2 = request.getParameter("answer2");
            String answer3 = request.getParameter("answer3");
            String subject = request.getParameter("subject");
            int subjects_id = subjectService.getSubjectId(subject);
// Надо форму переделать чтобы еще и язык выбирать
            Question questionFromWeb = new Question(question, answer1, answer2, answer3, correctAnswer, subjects_id, 2);
            questionService.add(questionFromWeb);
        }

        //request.getRequestDispatcher("/teacher/teachermath.jsp").forward(request, response);
        // response.sendRedirect(request.getContextPath() + "/teacher/teachermath.jsp");
    }
}
