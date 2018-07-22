package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.entity.Subject;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TeacherSubjectsCommand implements ActionCommand {

    private static final String TEACHER_LIST_SUBJECT_PATH = "path.page.teacher.list.subject";
    private static final String SUBJECT_LIST = "SubjectList";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String PAGE = "page";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private SubjectService subjectService = new SubjectService();

    private static final Logger LOGGER = LogManager.getLogger(TeacherSubjectsCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;
        List<Subject> list;
        int page;
        int recordsPerPage = 3;
        int noOfRecords;

        try {

            page = Integer.parseInt(requestContent.getParameter(PAGE));
            list = subjectService.numberOfAllSubjects((page - 1), recordsPerPage);
            noOfRecords = subjectService.getSizeAllSubjects();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            requestContent.setAttribute(SUBJECT_LIST, list);
            requestContent.setAttribute(NO_OF_PAGES, noOfPages);
            requestContent.setAttribute(CURRENT_PAGE, page);
            pageString = ConfigurationManager.getProperty(TEACHER_LIST_SUBJECT_PATH);

        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter page is found", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }


        return pageString;
    }
}
