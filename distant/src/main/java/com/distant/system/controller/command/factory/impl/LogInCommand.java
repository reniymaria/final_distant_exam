package com.distant.system.controller.command.factory.impl;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.util.Validation;
import com.distant.system.controller.util.HashUtil;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.UserService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final String EMPTY_MESS_1 = "emptyMess1";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String EMPTY_MESS_2 = "emptyMess2";
    private static final String AUTHORIZE_ERROR = "authorizeError";
    private static final String CON_ERROR_LOGIN_PASSWORD = "con.error.login.password";
    private static final String STUDENT = "student";
    private static final String ROLE = "role";
    private static final String STUDENT_ID = "studentID";
    private static final String NAME_SURNAME = "nameSurname";
    private static final String TEACHER = "teacher";
    private static final String NAME_SURNAME1 = "nameSurname";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private UserService userService = new UserService();

    private static final Logger LOGGER = LogManager.getLogger(LogInCommand.class);

    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        String login = null;
        String pass = null;
        try {
            login = requestContent.getParameter(LOGIN_PARAM);
            pass = requestContent.getParameter(PASSWORD_PARAM);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameters are not found", e);

        }


        String hashPass = HashUtil.md5Apache(pass);

        Locale locale = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter locale is not found");
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, locale);

        if (Validation.isEmpty(login)) {
            requestContent.setAttribute(EMPTY_MESS_1, bundle.getString(CON_FIELD_EMPTY));
            if (Validation.isEmpty(pass)) {
                requestContent.setAttribute(EMPTY_MESS_2, bundle.getString(CON_FIELD_EMPTY));
            }
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        } else if (Validation.isEmpty(pass)) {
            requestContent.setAttribute(EMPTY_MESS_2, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        } else {
            try {
                if (!userService.isAuthorized(login, hashPass)) {
                    requestContent.setAttribute(AUTHORIZE_ERROR, bundle.getString(CON_ERROR_LOGIN_PASSWORD));
                    page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
                } else if (userService.findPosition(login).equals(STUDENT)) {
                    requestContent.setSessionAttribute(ROLE, STUDENT);
                    requestContent.setSessionAttribute(STUDENT_ID, userService.findUserID(login));
                    requestContent.setSessionAttribute(NAME_SURNAME, userService.getNameSurname(login));
                    page = ConfigurationManager.getProperty(STUDENT_HOME_PAGE_PATH);
                } else if (userService.findPosition(login).equals(TEACHER)) {
                    requestContent.setSessionAttribute(ROLE, TEACHER);
                    requestContent.setSessionAttribute(NAME_SURNAME1, userService.getNameSurname(login));
                    page = ConfigurationManager.getProperty(TEACHER_HOME_PAGE_PATH);
                }
            } catch (ServiceException e) {
                return page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
            }
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
    }
}
