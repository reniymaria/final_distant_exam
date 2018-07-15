package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Subject;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ListStudentSubjectCommand implements ActionCommand {

    private static final String STUDENT_ID_ATTR = "studentID";
    private static final String EXAMING_LIST_PATH = "path.page.examing.list";
    private static final String PAGE = "page";
    private static final String SUBJECT_LIST = "SubjectList";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    
    private SubjectService subjectService = new SubjectService();


    private static final Logger LOGGER = LogManager.getLogger(ListStudentSubjectCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;


        int studentId = 0;
        try {
            studentId = (int) requestContent.getSessionAttribute(STUDENT_ID_ATTR);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }

        int page = 1;
        int recordsPerPage = 3;
        try {
            if (requestContent.getParameter(PAGE) != null) {
                page = Integer.parseInt(requestContent.getParameter(PAGE));
            }
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter page found", e);
            e.printStackTrace();
        }

        List<Subject> list = null;
        try {
            list = subjectService.numberOfStudentSubjects(studentId,(page - 1), recordsPerPage);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = subjectService.getSizeStudentAvailableSubjects(studentId);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        requestContent.setAttribute(SUBJECT_LIST, list);
        requestContent.setAttribute(NO_OF_PAGES, noOfPages);
        requestContent.setAttribute(CURRENT_PAGE, page);

        pageString = ConfigurationManager.getProperty(EXAMING_LIST_PATH);
        return pageString;
    }
}
