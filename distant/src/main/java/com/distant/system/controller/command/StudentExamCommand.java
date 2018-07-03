package com.distant.system.controller.command;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.util.DaoUtil;
import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.QuestionService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentExamCommand extends AbstractCommand {

    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        QuestionService questionService = new QuestionService();

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);


        String subject = request.getParameter("subject");
        String examlang = request.getParameter("examlang");


        List<Question> examQuestion = new ArrayList<>();
        try {
            examQuestion = DaoUtil.getRandomFive(questionService.getQuestions(subject, examlang));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        if (examQuestion.size() < 5) {
            request.setAttribute("questionNotEnough", bundle.getString("con.list.question.error"));
            request.getRequestDispatcher("/exam").forward(request, response);
        }

        session.setAttribute("examQuestions", examQuestion);
        request.getRequestDispatcher("/exam").forward(request, response);

    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
