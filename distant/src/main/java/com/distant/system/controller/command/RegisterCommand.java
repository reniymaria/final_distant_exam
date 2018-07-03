package com.distant.system.controller.command;

import com.distant.system.controller.util.FieldsUtil;
import com.distant.system.controller.util.HashUtil;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
import com.distant.system.service.daoservice.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterCommand extends AbstractCommand {


    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/register").forward(request, response);
    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        UserService userService = new UserService();
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        if (!FieldsUtil.isPasswordEqual(pass, repass)) {
            request.setAttribute("passwordError", bundle.getString("con.password.error"));
            request.getRequestDispatcher("/register").forward(request, response);
        } else if (FieldsUtil.isEmpty(login)) {
            request.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            request.getRequestDispatcher("/register").forward(request, response);
        } else if (FieldsUtil.isEmpty(pass)) {
            request.setAttribute("emptyMess2", bundle.getString("con.field.empty2"));
            request.getRequestDispatcher("/register").forward(request, response);
        } else if (FieldsUtil.isEmpty(repass)) {
            request.setAttribute("emptyMess3", bundle.getString("con.field.empty3"));
            request.getRequestDispatcher("/register").forward(request, response);
        } else if (FieldsUtil.isEmpty(name)) {
            request.setAttribute("emptyMess4", bundle.getString("con.field.empty4"));
            request.getRequestDispatcher("/register").forward(request, response);
        } else if (FieldsUtil.isEmpty(surname)) {
            request.setAttribute("emptyMess5", bundle.getString("con.field.empty5"));
            request.getRequestDispatcher("/register").forward(request, response);
        }
        try {
            if (userService.checkIfExist(login)) {
                request.setAttribute("loginError", bundle.getString("con.loginexist"));
                request.getRequestDispatcher("/register").forward(request, response);
            } else {
                String hashPass = HashUtil.md5Apache(pass);
                User user = new User(login, hashPass, name, surname, "student");
                userService.addStudent(user);
                request.getRequestDispatcher("/login").forward(request, response);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
