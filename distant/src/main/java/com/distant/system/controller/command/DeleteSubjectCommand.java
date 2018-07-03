package com.distant.system.controller.command;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.service.daoservice.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteSubjectCommand extends AbstractCommand {

    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/subjectlist");
    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        SubjectService subjectService = new SubjectService();
        HttpSession session = request.getSession(true);

        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));

        try {
            subjectService.deleteSubject(subjectId);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("msgdeletesubject", bundle.getString("con.msgdeletesubject"));

        request.getRequestDispatcher("/action_completed").forward(request, response);

    }
}
