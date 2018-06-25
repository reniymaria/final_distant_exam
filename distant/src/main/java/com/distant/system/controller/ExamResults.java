package com.distant.system.controller;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.service.daoservice.MarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/results")
public class ExamResults extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MarkService markService = new MarkService();

        System.out.println("Check what found: ");

        try {
            System.out.println(markService.getExamMarks());
        } catch (DaoException e) {
            e.printStackTrace();
        }

        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<ExamResult> listExam = null;
        try {
            listExam = markService.numberOfMarks((page - 1),
                    recordsPerPage);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = markService.allMarks();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("MarkList", listExam);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/teacher/examresults.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MarkService markService = new MarkService();
        HttpSession session = request.getSession(true);

        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        int subjectID = Integer.parseInt(request.getParameter("subjectID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        try {
            markService.deleteMark(userID, subjectID);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("errMsg", bundle.getString("con.value.is.deleted"));

        doGet(request, response);

    }
}
