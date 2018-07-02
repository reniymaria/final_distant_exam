package com.distant.system.controller;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/update")
public class UpdateQuestion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionService questionService = new QuestionService();
        int id = Integer.parseInt(request.getParameter("questionID"));
        Question question = null;
        try {
            question = questionService.find(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        request.setAttribute("question", question);
        request.getRequestDispatcher("/teacher/editquestion.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionService questionService = new QuestionService();

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String question = request.getParameter("question");
        int correctAnswer = Integer.parseInt(request.getParameter("correctAnswer"));
        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");
        String answer3 = request.getParameter("answer3");
        int subjectId =Integer.parseInt(request.getParameter("subjectId"));
        int langId = Integer.parseInt(request.getParameter("langId"));

        Question questionFromWeb = new Question(questionId, question, answer1, answer2, answer3, correctAnswer);
        try {
            questionService.update(questionFromWeb);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("subjectId", subjectId);
        request.setAttribute("langId", langId);
        request.setAttribute("msgeditquestion", bundle.getString("con.msgeditquestion"));
        request.getRequestDispatcher("/teacher/actioncompleted.jsp").forward(request, response);

    }

}
