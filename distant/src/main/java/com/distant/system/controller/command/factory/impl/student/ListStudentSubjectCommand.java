package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.entity.Subject;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.entity.User;
import com.distant.system.service.SubjectService;
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

    private SubjectService subjectService = new SubjectService();


    private static final Logger LOGGER = LogManager.getLogger(ListStudentSubjectCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;
        int studentId;
        int page;
        int recordsPerPage = 3;
        int noOfRecords;
        List<Subject> list;

        try {
            User user = (User) requestContent.getSessionAttribute(USER);
            studentId = user.getUserID();
            page = Integer.parseInt(requestContent.getParameter(PAGE));
            list = subjectService.numberOfStudentSubjects(studentId, (page - 1), recordsPerPage);
            noOfRecords = subjectService.getSizeStudentAvailableSubjects(studentId);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            requestContent.setAttribute(SUBJECT_LIST, list);
            requestContent.setAttribute(NO_OF_PAGES, noOfPages);
            requestContent.setAttribute(CURRENT_PAGE, page);
            pageString = ConfigurationManager.getProperty(EXAMING_LIST_PATH);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            pageString = ConfigurationManager.getProperty(EXAMING_LIST_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return pageString;
    }
}
