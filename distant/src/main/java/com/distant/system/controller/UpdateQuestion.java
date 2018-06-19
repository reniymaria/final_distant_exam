package com.distant.system.controller;

import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateQuestion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionService questionService = new QuestionService();
        int id = Integer.parseInt(request.getParameter("questionID"));
        Question question = questionService.find(id);
        request.setAttribute("question", question);
        request.getRequestDispatcher("/teacher/editquestion.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionService questionService = new QuestionService();

        int id = Integer.parseInt(request.getParameter("id"));
        String question = request.getParameter("question");
        int correctAnswer = Integer.parseInt(request.getParameter("correctAnswer"));
        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");
        String answer3 = request.getParameter("answer3");

        Question questionFromWeb = new Question(id, question, answer1, answer2, answer3, correctAnswer);
        questionService.update(questionFromWeb);
        request.getRequestDispatcher("/questions").forward(request, response);

    }

}
