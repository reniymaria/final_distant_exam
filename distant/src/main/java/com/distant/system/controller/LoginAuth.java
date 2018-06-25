package com.distant.system.controller;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.service.daoservice.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/loginpage")
public class LoginAuth extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        HttpSession session = request.getSession(true);

        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content",locale);

        if (login == null || pass == null) {
            session.setAttribute("errMsg", "Please Enter Login and Password");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
         return;
        } else {

            try {
                if (!userService.isAuthorized(login,pass)) {
                    request.setAttribute("errMsg", bundle.getString("con.errorloginpassword"));
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (userService.findPosition(login).equals("student")) {
                    session.setAttribute("role", "student");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
