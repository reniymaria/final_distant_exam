package com.distant.system.controller;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/questions")
public class ListQuestionsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();

        int subjectId = 0;
        int langId = 0;


        Cookie ck[] = request.getCookies();
        for (int i = 0; i < ck.length; i++) {
            if (ck[i].getName().equals("subjectCk")) {
                subjectId = Integer.parseInt(ck[i].getValue());
            } else if (ck[i].getName().equals("languageCk")) {
                langId = Integer.parseInt(ck[i].getValue());
            }
        }

        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Question> list = null;
        try {
            list = questionService.numberOfQuestions(subjectId, langId, (page - 1),
                    recordsPerPage);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = questionService.allQuestions(subjectId, langId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("QuestionList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);


        request.setAttribute("subjectId", subjectId);
        request.setAttribute("langId", langId);
        request.getRequestDispatcher("/teacher/listofquestions.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectId = request.getParameter("subjectId");
        String languageId = request.getParameter("langId");
        Cookie subjectCk = new Cookie("subjectCk", subjectId);
        Cookie languageCk = new Cookie("languageCk", languageId);
        response.addCookie(subjectCk);
        response.addCookie(languageCk);
        doGet(request, response);
    }
}
