package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.controller.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentExamListCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String MARK_RESULTS_PATH = "path.page.student.marks";
    private static final String STUDENT_ID = "studentID";
    private static final String LIST_EMPTY = "listEmpty";
    private static final String CON_LIST_EMPTY = "con.list.empty";
    private static final String PAGE = "page";
    private static final String EXAM_LIST = "ExamList";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";

    private MarkService markService = new MarkService();

    private static final Logger LOGGER = LogManager.getLogger(StudentExamListCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageList;

        Locale locale = null;
        int studentId = 0;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            studentId = (int) requestContent.getSessionAttribute(STUDENT_ID);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);


        try {
            if (markService.allStudentMarks(studentId) == 0) {
                requestContent.setAttribute(LIST_EMPTY, bundle.getString(CON_LIST_EMPTY));
            }
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }


        int page = 1;
        int recordsPerPage = 3;
        try {
            if (requestContent.getParameter(PAGE) != null) {
                page = Integer.parseInt(requestContent.getParameter(PAGE));
            }
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter page is found ", e);
            e.printStackTrace();
        }

        List<ExamResult> list = null;
        try {
            list = markService.numberOfStudentMarks(studentId, (page - 1), recordsPerPage);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = markService.allStudentMarks(studentId);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        requestContent.setAttribute(EXAM_LIST, list);
        requestContent.setAttribute(NO_OF_PAGES, noOfPages);
        requestContent.setAttribute(CURRENT_PAGE, page);

        pageList = ConfigurationManager.getProperty(MARK_RESULTS_PATH);

        return pageList;
    }
}
