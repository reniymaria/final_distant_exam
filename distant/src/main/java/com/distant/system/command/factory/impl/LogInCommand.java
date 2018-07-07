package com.distant.system.command.factory.impl;

import com.distant.system.command.ActionCommand;
import com.distant.system.util.FieldsUtil;
import com.distant.system.util.HashUtil;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.UserService;
import com.distant.system.util.ConfigurationManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class LogInCommand implements ActionCommand {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String LANGUAGE = "language";
    private static final String BASE_NAME = "i18n.content";
    private static final String LOGIN_PAGE_PATH = "path.page.login";
    private static final String TEACHER_HOME_PAGE_PATH = "path.page.teacher.home";
    private static final String STUDENT_HOME_PAGE_PATH = "path.page.student.home";

    // private static final Logger logger = LogManager.getLogger();

    private UserService userService = new UserService();


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        String login = null;
        String pass = null;
        try {
            login = requestContent.getParameter(LOGIN_PARAM);
            pass = requestContent.getParameter(PASSWORD_PARAM);
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }


        String hashPass = HashUtil.md5Apache(pass);

        Locale locale = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);

        if (FieldsUtil.isEmpty(login)) {
            requestContent.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            if (FieldsUtil.isEmpty(pass)) {
                requestContent.setAttribute("emptyMess2", bundle.getString("con.field.empty"));
            }
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        } else if (FieldsUtil.isEmpty(pass)) {
            requestContent.setAttribute("emptyMess2", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        } else {
            try {
                if (!userService.isAuthorized(login, hashPass)) {
                    requestContent.setAttribute("authorizeError", bundle.getString("con.error.login.password"));
                    page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
                } else if (userService.findPosition(login).equals("student")) {
                    requestContent.setSessionAttribute("role", "student");
                    requestContent.setSessionAttribute("studentID", userService.findUserID(login));
                    requestContent.setSessionAttribute("nameSurname", userService.getNameSurname(login));
                    page = ConfigurationManager.getProperty(STUDENT_HOME_PAGE_PATH);
                } else if (userService.findPosition(login).equals("teacher")) {
                    requestContent.setSessionAttribute("role", "teacher");
                    requestContent.setSessionAttribute("nameSurname", userService.getNameSurname(login));
                    page = ConfigurationManager.getProperty(TEACHER_HOME_PAGE_PATH);
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
    }
}
