package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenEditSubjectCommand implements ActionCommand {


    private static final String EDIT_SUBJECT_PATH = "path.page.edit.subject";
    private static final String SUBJECT_ATTR = "subject";
    private static final String SUBJECT_ID = "subjectId";
    private static final String SUBJECT_SESSION = "subjectSession";


    private static final Logger logger = LogManager.getLogger(EditSubjectCommand.class);

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        String subject;
        int subjectId;
        try {
            subject = requestContent.getParameter(SUBJECT_ATTR);
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID));
            Subject subjectSession = new Subject(subjectId, subject);
            requestContent.setSessionAttribute(SUBJECT_SESSION, subjectSession);
            page = ConfigurationManager.getProperty(EDIT_SUBJECT_PATH);
        } catch (NoSuchRequestParameterException e) {
            logger.warn("Parameter is not found", e);
            page = ConfigurationManager.getProperty(EDIT_SUBJECT_PATH);
        }
        return page;
    }
}
