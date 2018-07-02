package com.distant.system.command;

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

public class StudentExamCommand extends AbstractCommand {
    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        QuestionService questionService = new QuestionService();
        HttpSession session = request.getSession(true);
        String subject = request.getParameter("subject");
        String examlang = request.getParameter("examlang");
        List<Question> examQuestion = new ArrayList<>();
        try {
            examQuestion = DaoUtil.getRandomFive(questionService.getQuestions(subject, examlang));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        session.setAttribute("examQuestions", examQuestion);
        request.getRequestDispatcher("student/exam.jsp").forward(request, response);

    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
