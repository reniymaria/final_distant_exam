package com.distant.system.command.factory.impl;

import com.distant.system.command.ActionCommand;
import com.distant.system.util.FieldsUtil;
import com.distant.system.util.HashUtil;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.UserService;
import com.distant.system.util.ConfigurationManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterCommand implements ActionCommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPASSWORD = "repassword";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String REGISTER_PATH_PAGE = "path.page.register";
    private static final String LOGIN_PATH_PAGE = "path.page.login";

    private UserService userService = new UserService();


    @Override
    public String executePost(SessionRequestContent requestContent) {

        String page = null;
        String login = null;
        Locale locale = null;
        String pass = null;
        String repass = null;
        String name = null;
        String surname = null;

        try {
            login = requestContent.getParameter(LOGIN);
            pass = requestContent.getParameter(PASSWORD);
            repass = requestContent.getParameter(REPASSWORD);
            name = requestContent.getParameter(NAME);
            surname = requestContent.getParameter(SURNAME);
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        if (!FieldsUtil.isPasswordEqual(pass, repass)) {
            requestContent.setAttribute("passwordError", bundle.getString("con.password.error"));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(login)) {
            requestContent.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(pass)) {
            requestContent.setAttribute("emptyMess2", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(repass)) {
            requestContent.setAttribute("emptyMess3", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(name)) {
            requestContent.setAttribute("emptyMess4", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(surname)) {
            requestContent.setAttribute("emptyMess5", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else {
            try {
                if (userService.checkIfExist(login)) {
                    requestContent.setAttribute("loginError", bundle.getString("con.loginexist"));
                    page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
                } else {
                    String hashPass = HashUtil.md5Apache(pass);
                    User user = new User(login, hashPass, name, surname, "student");
                    userService.addStudent(user);
                    requestContent.setAttribute("registerMessage", bundle.getString("con.register.meg"));
                    page = ConfigurationManager.getProperty(LOGIN_PATH_PAGE);
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
    }
}
