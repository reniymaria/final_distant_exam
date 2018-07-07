package com.distant.system.command.factory.impl.teacher;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.util.ConfigurationManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteSubjectCommand implements ActionCommand {
    private static final String I18N_CONTENT = "i18n.content";
    private static final String LANGUAGE = "language";
    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String ACTION_COMPLETED = "path.page.action.completed";

    private SubjectService subjectService = new SubjectService();


    @Override
    public String executePost(SessionRequestContent requestContent) {

        String page = null;
        Locale locale = null;
        int subjectId = 0;

        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_ATTR));
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        try {

            subjectService.deleteSubject(subjectId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        requestContent.setAttribute("msgdeletesubject", bundle.getString("con.msgdeletesubject"));
        page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
