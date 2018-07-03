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

@WebServlet("/exam_list")
public class StudentExamListServlet extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MarkService markService =  new MarkService();

        HttpSession session = request.getSession();
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        int studentId = (int) session.getAttribute("studentID");

        try {
            if(markService.allStudentMarks(studentId) == 0){
                request.setAttribute("listEmpty", bundle.getString("con.list.empty"));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }


        int page = 1;
        int recordsPerPage = 3;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<ExamResult> list = null;
        try {
            list = markService.numberOfStudentMarks(studentId,(page - 1), recordsPerPage);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = markService.allStudentMarks(studentId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("ExamList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("/mark_results").forward(request, response);

    }
}
