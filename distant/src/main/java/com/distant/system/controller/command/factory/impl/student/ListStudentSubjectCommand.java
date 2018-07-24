package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.entity.Subject;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.entity.User;
import com.distant.system.service.ServiceFactory;
import com.distant.system.service.SubjectService;
import com.distant.system.service.impl.SubjectServiceImpl;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ListStudentSubjectCommand implements ActionCommand {

    private static final String EXAMING_LIST_PATH = "path.page.examing.list";
    private static final String PAGE = "page";
    private static final String SUBJECT_LIST = "SubjectList";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String USER = "user";

    private SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();


    private static final Logger logger = LogManager.getLogger(ListStudentSubjectCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        String pageString;
        int page;

        try {

            page = Integer.parseInt(requestContent.getParameter(PAGE));
            pageString = createListSubjects(page, requestContent);

        } catch (NoSuchRequestParameterException e) {
            logger.warn("No such parameter", e);
            page = 1;
            pageString = createListSubjects(page, requestContent);
        }

        return pageString;
    }

    private String createListSubjects(int page, SessionRequestContent requestContent) {

        String pageString;
        int studentId;
        int recordsPerPage = 3;
        int noOfRecords;
        List<Subject> list;

        try {
            User user = (User) requestContent.getSessionAttribute(USER);
            studentId = user.getUserID();

            list = subjectService.numberOfStudentSubjects(studentId, (page - 1), recordsPerPage);
            noOfRecords = subjectService.getSizeStudentAvailableSubjects(studentId);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            requestContent.setAttribute(SUBJECT_LIST, list);
            requestContent.setAttribute(NO_OF_PAGES, noOfPages);
            requestContent.setAttribute(CURRENT_PAGE, page);

            pageString = ConfigurationManager.getProperty(EXAMING_LIST_PATH);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (NoSuchRequestParameterException e) {
            logger.warn("No such parameter", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return pageString;
    }
}
