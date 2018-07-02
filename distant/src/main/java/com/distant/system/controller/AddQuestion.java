package com.distant.system.controller;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.LanguageService;
import com.distant.system.service.daoservice.QuestionService;
import com.distant.system.service.daoservice.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/addquestion")
public class AddQuestion extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();

        String question = request.getParameter("question");
        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");
        String answer3 = request.getParameter("answer3");
        int correctAnswer = Integer.parseInt(request.getParameter("correctAnswer"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int langId = Integer.parseInt(request.getParameter("langId"));

        Question questionFromWeb = new Question(question, answer1, answer2, answer3, correctAnswer, subjectId, langId);

        try {
            questionService.add(questionFromWeb);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        request.setAttribute("msgaddquestion", bundle.getString("con.msgaddquestion"));

        request.setAttribute("subjectId", subjectId);
        request.setAttribute("langId", langId);

        request.getRequestDispatcher("/teacher/actioncompleted.jsp").forward(request, response);

    }
}
