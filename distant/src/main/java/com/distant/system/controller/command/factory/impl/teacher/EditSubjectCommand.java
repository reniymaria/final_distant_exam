package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.util.FieldsUtil;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class EditSubjectCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String EDIT_SUBJECT_PATH = "path.page.edit.subject";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String CON_MSGEDITSUBJECT = "con.msgeditsubject";
    private static final String MSGEDITSUBJECT = "msgeditsubject";
    private static final String EMPTY_MESS_1 = "emptyMess1";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String SUBJECT_ATTR = "subject";
    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String SUBJECT_ID = "subjectId";

    private SubjectService subjectService = new SubjectService();

    private static final Logger LOGGER = LogManager.getLogger(EditSubjectCommand.class);



    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;

        Locale locale = null;
        int subjectId = 0;
        String subject = null;


        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }
        try {
            subjectId = Integer.parseInt(String.valueOf(requestContent.getSessionAttribute(SUBJECT_ID)));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }
        try {
            subject = requestContent.getParameter(SUBJECT_ATTR);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        if (FieldsUtil.isEmpty(subject)) {
            requestContent.setAttribute(EMPTY_MESS_1, bundle.getString(CON_FIELD_EMPTY));
            requestContent.setAttribute(SUBJECT_ATTR, subject);
            page = ConfigurationManager.getProperty(EDIT_SUBJECT_PATH);
            return page;
        } else {
            try {
                requestContent.removeSessionAttribute(SUBJECT_ID_ATTR);
                subjectService.updateSubject(subjectId, subject);
            } catch (DaoException e) {
                LOGGER.error("DAO exception", e);
                e.printStackTrace();
            }

            requestContent.setAttribute(MSGEDITSUBJECT, bundle.getString(CON_MSGEDITSUBJECT));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);

        }
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        String page;

        int subjectId = 0;
        String subject = null;
        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_ATTR));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }
        try {
            subject = requestContent.getParameter(SUBJECT_ATTR);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }

        if (subjectId > 0) {
            requestContent.setSessionAttribute(SUBJECT_ID_ATTR, subjectId);
        }

        requestContent.setAttribute(SUBJECT_ATTR, subject);
        page = ConfigurationManager.getProperty(EDIT_SUBJECT_PATH);
        return page;
    }
}
