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

    private static final Logger LOGGER = LogManager.getLogger(AddSubjectCommand.class);

    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;

        Locale locale = null;
        String subject = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            subject = requestContent.getParameter(SUBJECT_PARAM);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found");
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
                LOGGER.warn("Dao exception foe subject",e);
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