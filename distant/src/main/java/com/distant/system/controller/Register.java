package com.distant.system.controller;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
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

@WebServlet("/register")
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content",locale);


        try {
            if(userService.checkIfExist(login)){
                request.setAttribute("errMsg", bundle.getString("con.loginexist"));
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            else {
                User user = new User(login,pass,name,surname,"student");
                userService.addStudent(user);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

}
