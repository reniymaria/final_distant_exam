package com.distant.system.controller.command;

import com.distant.system.controller.util.FieldsUtil;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.service.daoservice.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddSubjectCommand extends AbstractCommand {

    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        SubjectService subjectService = new SubjectService();

        String subject = request.getParameter("subject");

        if (FieldsUtil.isEmpty(subject)) {
            request.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            request.getRequestDispatcher("/add_subject").forward(request, response);
        } else {
            try {
                subjectService.addSubject(subject);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            request.setAttribute("msgaddsubject", bundle.getString("con.msgaddsubject"));
            request.getRequestDispatcher("/action_completed").forward(request, response);
        }
    }
}
