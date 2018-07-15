package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteSubjectCommand implements ActionCommand {
    private static final String I18N_CONTENT = "i18n.content";
    private static final String LANGUAGE = "language";
    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String MSGDELETESUBJECT = "msgdeletesubject";
    private static final String CON_MSGDELETESUBJECT = "con.msgdeletesubject";

    private SubjectService subjectService = new SubjectService();

    private static final Logger LOGGER = LogManager.getLogger(DeleteSubjectCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {

        String page;
        Locale locale = null;
        int subjectId = 0;

        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_ATTR));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        try {

            subjectService.deleteSubject(subjectId);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        requestContent.setAttribute(MSGDELETESUBJECT, bundle.getString(CON_MSGDELETESUBJECT));
        page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
