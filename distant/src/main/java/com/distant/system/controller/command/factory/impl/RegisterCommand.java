package com.distant.system.controller.command.factory.impl;

import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.controller.util.FieldsUtil;
import com.distant.system.controller.util.HashUtil;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
import com.distant.system.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final String PASSWORD_ERROR = "passwordError";
    private static final String EMPTY_MESS_1 = "emptyMess1";
    private static final String EMPTY_MESS_2 = "emptyMess2";
    private static final String EMPTY_MESS_3 = "emptyMess3";
    private static final String EMPTY_MESS_4 = "emptyMess4";
    private static final String EMPTY_MESS_5 = "emptyMess5";
    private static final String CON_PASSWORD_ERROR = "con.password.error";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String LOGIN_ERROR = "loginError";
    private static final String CON_LOGINEXIST = "con.loginexist";
    private static final String STUDENT = "student";
    private static final String REGISTER_MESSAGE = "registerMessage";
    private static final String CON_REGISTER_MEG = "con.register.meg";

    private UserService userService = new UserService();

    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

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
            LOGGER.warn("Parameters are not found");
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        if (!FieldsUtil.isPasswordEqual(pass, repass)) {
            requestContent.setAttribute(PASSWORD_ERROR, bundle.getString(CON_PASSWORD_ERROR));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(login)) {
            requestContent.setAttribute(EMPTY_MESS_1, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(pass)) {
            requestContent.setAttribute(EMPTY_MESS_2, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(repass)) {
            requestContent.setAttribute(EMPTY_MESS_3, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(name)) {
            requestContent.setAttribute(EMPTY_MESS_4, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(surname)) {
            requestContent.setAttribute(EMPTY_MESS_5, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
        } else {
            try {
                if (userService.checkIfExist(login)) {
                    requestContent.setAttribute(LOGIN_ERROR, bundle.getString(CON_LOGINEXIST));
                    page = ConfigurationManager.getProperty(REGISTER_PATH_PAGE);
                } else {
                    String hashPass = HashUtil.md5Apache(pass);
                    User user = new User(login, hashPass, name, surname, STUDENT);
                    userService.addStudent(user);
                    requestContent.setAttribute(REGISTER_MESSAGE, bundle.getString(CON_REGISTER_MEG));
                    page = ConfigurationManager.getProperty(LOGIN_PATH_PAGE);
                }
            } catch (DaoException e) {
                LOGGER.error("Student is not added",e);
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
