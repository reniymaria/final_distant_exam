package com.distant.system.command.factory.impl.teacher;

import com.distant.system.command.ActionCommand;
import com.distant.system.entity.Subject;
import com.distant.system.util.FieldsUtil;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.util.ConfigurationManager;

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

    private SubjectService subjectService = new SubjectService();


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        Locale locale = null;
        int subjectId = 0;
        String subject = null;


        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        try {
            subjectId = Integer.parseInt(String.valueOf(requestContent.getSessionAttribute("subjectId")));
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        try {
            subject = requestContent.getParameter(SUBJECT_ATTR);
        } catch (NoSuchRequestParameterException e) {
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
            e.printStackTrace();
        }
        try {
            subject = requestContent.getParameter(SUBJECT_ATTR);
        } catch (NoSuchRequestParameterException e) {
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
