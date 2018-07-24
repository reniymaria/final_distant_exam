package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.ServiceFactory;
import com.distant.system.service.SubjectService;
import com.distant.system.service.impl.SubjectServiceImpl;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class DeleteSubjectCommand implements ActionCommand {

    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String MSGDELETESUBJECT = "msgdeletesubject";
    private static final String CON_MSGDELETESUBJECT = "con.msgdeletesubject";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();

    private static final Logger logger = LogManager.getLogger(DeleteSubjectCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;
        int subjectId;
        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_ATTR));
            subjectService.deleteSubject(subjectId);
            requestContent.setAttribute(MSGDELETESUBJECT, bundle.getString(CON_MSGDELETESUBJECT));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        } catch (NoSuchRequestParameterException e) {
            logger.warn("Parameter is not found", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return page;
    }

}
