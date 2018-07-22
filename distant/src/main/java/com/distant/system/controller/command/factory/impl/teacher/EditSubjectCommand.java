package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.Subject;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class EditSubjectCommand implements ActionCommand {


    private static final String EDIT_SUBJECT_PATH = "path.page.edit.subject";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String CON_MSGEDITSUBJECT = "con.msgeditsubject";
    private static final String MSGEDITSUBJECT = "msgeditsubject";
    private static final String EMPTY_MESS_1 = "emptyMess1";
    private static final String SUBJECT_ATTR = "subject";
    private static final String SUBJECT_ID = "subjectId";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String SUBJECT_SESSION = "subjectSession";

    private SubjectService subjectService = new SubjectService();

    private static final Logger LOGGER = LogManager.getLogger(EditSubjectCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {

        String page;
        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);
        String subject;
        Subject subjectSession;

        try {
            subject = requestContent.getParameter(SUBJECT_ATTR);
            subjectSession = (Subject) requestContent.getSessionAttribute(SUBJECT_SESSION);

            subjectService.updateSubject(subjectSession.getSubjectID(), subject);

            requestContent.removeSessionAttribute(SUBJECT_SESSION);
            requestContent.setAttribute(MSGEDITSUBJECT, bundle.getString(CON_MSGEDITSUBJECT));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ValidationException e) {
            LOGGER.warn("Validation exception", e);
            requestContent.setAttribute(EMPTY_MESS_1, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(EDIT_SUBJECT_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

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
            LOGGER.warn("Parameter is not found", e);
            page = ConfigurationManager.getProperty(EDIT_SUBJECT_PATH);
        }
        return page;
    }

}
