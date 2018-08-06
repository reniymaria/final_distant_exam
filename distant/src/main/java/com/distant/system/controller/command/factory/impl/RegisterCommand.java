package com.distant.system.controller.command.factory.impl;

import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.ServiceFactory;
import com.distant.system.service.UserService;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.service.util.Validation;
import com.distant.system.entity.User;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class RegisterCommand implements ActionCommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPASSWORD = "repassword";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String REGISTER_PATH_PAGE = "path.page.register";
    private static final String LOGIN_PATH_PAGE = "path.page.login";
    private static final String CON_PASSWORD_ERROR = "con.password.error";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String STUDENT = "student";
    private static final String REGISTER_MESSAGE = "registerMessage";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String ERR_MESS = "errMess";
    private static final String CON_REGISTER_SUCCESS = "con.register.success";

    private UserService userService = ServiceFactory.getInstance().getUserService();

    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;
        String login;
        String pass;
        String repass;
        String name;
        String surname;

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        try {
            login = requestContent.getParameter(LOGIN);
            pass = requestContent.getParameter(PASSWORD);
            repass = requestContent.getParameter(REPASSWORD);
            name = requestContent.getParameter(NAME);
            surname = requestContent.getParameter(SURNAME);

            if (!Validation.isPasswordEqual(pass, repass)) {
                requestContent.setAttribute(ERR_MESS, bundle.getString(CON_PASSWORD_ERROR));
                page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
            } else {
                User user = new User(login, pass, name, surname, STUDENT);
                userService.addStudent(user);
                requestContent.setAttribute(REGISTER_MESSAGE, bundle.getString(CON_REGISTER_SUCCESS));
                page = ConfigurationManager.getProperty(LOGIN_PATH_PAGE);
            }
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameters are not found", e);
            requestContent.setAttribute(ERR_MESS, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } catch (ValidationException e) {
            LOGGER.warn("Validation exception");
            requestContent.setAttribute(ERR_MESS, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("Register service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return page;
    }
}
