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

public class EditSubjectCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String EDIT_SUBJECT_PATH = "path.page.edit.subject";
    private static final String ACTION_COMPLETED = "path.page.action.completed";

    private SubjectService subjectService = new SubjectService();


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        Locale locale = null;
        int subjectId = 0;
        String subject = null;

        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            subjectId = Integer.parseInt(requestContent.getParameter("subjectId"));
            subject = requestContent.getParameter("subject");
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        if (FieldsUtil.isEmpty(subject)) {
            requestContent.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            requestContent.setAttribute("subjectId", subjectId);
            page = ConfigurationManager.getProperty(EDIT_SUBJECT_PATH);
            return page;
        } else {
            try {
                subjectService.updateSubject(subjectId, subject);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            requestContent.setAttribute("msgeditsubject", bundle.getString("con.msgeditsubject"));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);

        }
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
