package com.distant.system.command.factory.impl.teacher;

import com.distant.system.command.ActionCommand;
import com.distant.system.util.FieldsUtil;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.util.ConfigurationManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class AddSubjectCommand implements ActionCommand {


    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String ADD_SUBJECT_PATH_PAGE = "path.page.add.subject";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String SUBJECT_PARAM = "subject";
    private static final String EMPTY_MESS_1_ATTR = "emptyMess1";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String MSGADDSUBJECT_ATTR = "msgaddsubject";
    private static final String CON_MSGADDSUBJECT = "con.msgaddsubject";

    private SubjectService subjectService = new SubjectService();

    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;

        Locale locale = null;
        String subject = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            subject = requestContent.getParameter(SUBJECT_PARAM);
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);



        if (FieldsUtil.isEmpty(subject)) {
            requestContent.setAttribute(EMPTY_MESS_1_ATTR, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(ADD_SUBJECT_PATH_PAGE);
           return page;
        } else {
            try {
                subjectService.addSubject(subject);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            requestContent.setAttribute(MSGADDSUBJECT_ATTR, bundle.getString(CON_MSGADDSUBJECT));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
