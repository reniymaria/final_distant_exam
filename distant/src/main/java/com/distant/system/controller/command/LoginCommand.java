package com.distant.system.controller.command;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.distant.system.controller.util.FieldsUtil;
import com.distant.system.controller.util.HashUtil;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.service.daoservice.UserService;

public class LoginCommand extends AbstractCommand {

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        UserService userService = new UserService();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        String hashPass = HashUtil.md5Apache(pass);

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        if (FieldsUtil.isEmpty(login)) {
            request.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            if (FieldsUtil.isEmpty(pass)) {
                request.setAttribute("emptyMess2", bundle.getString("con.field.empty2"));
            }
            request.getRequestDispatcher("/login").forward(request, response);
        } else if (FieldsUtil.isEmpty(pass)) {
            request.setAttribute("emptyMess2", bundle.getString("con.field.empty2"));
            request.getRequestDispatcher("/login").forward(request, response);
        } else {
            try {
                if (!userService.isAuthorized(login, hashPass)) {
                    request.setAttribute("authorizeError", bundle.getString("con.error.login.password"));
                    request.getRequestDispatcher("/login").forward(request, response);
                } else if (userService.findPosition(login).equals("student")) {
                    session.setAttribute("role", "student");
                    session.setAttribute("studentID", userService.findUserID(login));
                    session.setAttribute("nameSurname", userService.getNameSurname(login));
                    response.sendRedirect(request.getContextPath() + "/student_home");
                } else if (userService.findPosition(login).equals("teacher")) {
                    session.setAttribute("role", "teacher");
                    session.setAttribute("nameSurname", userService.getNameSurname(login));
                    response.sendRedirect(request.getContextPath() + "/teacher_home");
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.getRequestDispatcher("/login").forward(request, response);

    }

}
