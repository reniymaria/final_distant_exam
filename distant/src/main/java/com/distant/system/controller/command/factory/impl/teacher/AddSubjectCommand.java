package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class AddSubjectCommand implements ActionCommand {


    private static final String ADD_SUBJECT_PATH_PAGE = "path.page.add.subject";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String SUBJECT_PARAM = "subject";
    private static final String EMPTY_MESS_1_ATTR = "emptyMess1";
    private static final String MSGADDSUBJECT_ATTR = "msgaddsubject";
    private static final String CON_MSGADDSUBJECT = "con.msgaddsubject";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private SubjectService subjectService = new SubjectService();

    private static final Logger LOGGER = LogManager.getLogger(AddSubjectCommand.class);

    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);
        String subject;
        try {
            subject = requestContent.getParameter(SUBJECT_PARAM);
            subjectService.addSubject(subject);
            requestContent.setAttribute(MSGADDSUBJECT_ATTR, bundle.getString(CON_MSGADDSUBJECT));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found");
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ValidationException e) {
            LOGGER.warn("Validation exception");
            requestContent.setAttribute(EMPTY_MESS_1_ATTR, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(ADD_SUBJECT_PATH_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return page;
}

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
