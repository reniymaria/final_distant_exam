package com.distant.system.command;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.service.daoservice.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditSubjectCommand extends AbstractCommand {
    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String subject = request.getParameter("subject");

        request.setAttribute("subjectId", subjectId);
        request.setAttribute("subject",subject);


        request.getRequestDispatcher("teacher/editsubject.jsp").forward(request, response);


    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        SubjectService subjectService = new SubjectService();

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String subject = request.getParameter("subject");

        try {
            subjectService.updateSubject(subjectId, subject);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("msgeditsubject", bundle.getString("con.msgeditsubject"));

        request.getRequestDispatcher("teacher/actioncompleted.jsp").forward(request, response);
    }
}
