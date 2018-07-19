package com.distant.system.controller.command.factory.impl;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.User;
import com.distant.system.service.UserService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class LogInCommand implements ActionCommand {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String LOGIN_PAGE_PATH = "path.page.login";
    private static final String TEACHER_HOME_PAGE_PATH = "path.page.teacher.home";
    private static final String STUDENT_HOME_PAGE_PATH = "path.page.student.home";
    private static final String ERR_MESS = "errMess";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String STUDENT = "student";
    private static final String TEACHER = "teacher";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String USER = "user";

    private UserService userService = new UserService();

    private static final Logger LOGGER = LogManager.getLogger(LogInCommand.class);

    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        String login;
        String pass;

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);
        User user;

        try {
            login = requestContent.getParameter(LOGIN_PARAM);
            pass = requestContent.getParameter(PASSWORD_PARAM);

            user = userService.logIn(login, pass);

            if (user.getLogin() != null) {
                if (user.getRole().equals(STUDENT)) {
                    requestContent.setSessionAttribute(USER, user);
                    page = ConfigurationManager.getProperty(STUDENT_HOME_PAGE_PATH);
                } else if (user.getRole().equals(TEACHER)) {
                    requestContent.setSessionAttribute(USER, user);
                    page = ConfigurationManager.getProperty(TEACHER_HOME_PAGE_PATH);
                }
            }
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameters are not found", e);
            requestContent.setAttribute(ERR_MESS, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        } catch (ValidationException e) {
            LOGGER.warn("Validation error", e);
            requestContent.setAttribute(ERR_MESS, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
        } catch (ServiceException e) {
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
    }
}
