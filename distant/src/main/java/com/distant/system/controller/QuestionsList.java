package com.distant.system.controller;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.QuestionService;
import com.distant.system.service.daoservice.SubjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/questions")
public class QuestionsList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        String subject = request.getParameter("subject");
        String language = request.getParameter("lang");

        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Question> list = null;
        try {
            list = questionService.numberOfQuestions(subject, language, (page - 1),
                    recordsPerPage);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = questionService.allQuestions(subject, language);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("QuestionList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/teacher/listofquestions.jsp").forward(request, response);
    }

}
