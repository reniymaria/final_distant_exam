package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class TeacherDeleteExamResultCommand implements ActionCommand {

    private static final String SUBJECT_ID_PARAM = "subjectID";
    private static final String USER_ID_PARAM = "userID";
    private static final String ERR_MSG_ATTR = "examDeletedMsg";
    private static final String CON_VALUE_IS_DELETED = "con.value.is.deleted";
    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private MarkService markService = new MarkService();

    private static final Logger LOGGER = LogManager.getLogger(TeacherDeleteExamResultCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;

        Locale locale = null;

        int subjectID = 0;
        int userID = 0;

        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }
        try {
            subjectID = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_PARAM));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }
        try {
            userID = Integer.parseInt(requestContent.getParameter(USER_ID_PARAM));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        try {
            markService.deleteMark(userID, subjectID);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            return page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        requestContent.setAttribute(ERR_MSG_ATTR, bundle.getString(CON_VALUE_IS_DELETED));
        page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        return page;

    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
