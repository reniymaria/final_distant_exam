package com.distant.system.command;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.service.daoservice.UserService;

public class LoginCommand extends AbstractCommand {

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        UserService userService = new UserService();
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        HttpSession session = request.getSession(true);

        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        if (login == null || pass == null) {
            session.setAttribute("errMsg", "Please Enter Login and Password");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } else {

            try {
                if (!userService.isAuthorized(login, pass)) {
                    request.setAttribute("errMsg", bundle.getString("con.errorloginpassword"));
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (userService.findPosition(login).equals("student")) {
                    session.setAttribute("role", "student");
                    session.setAttribute("studentID", userService.findUserID(login));
                    response.sendRedirect(request.getContextPath() + "/student/studenthome.jsp");
                } else if (userService.findPosition(login).equals("teacher")) {
                    session.setAttribute("role", "teacher");
                    response.sendRedirect(request.getContextPath() + "/teacher/teacherhome.jsp");
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }

}
