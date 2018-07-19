package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.User;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class StudentExamListCommand implements ActionCommand {

    private static final String MARK_RESULTS_PATH = "path.page.student.marks";
    private static final String LIST_EMPTY = "listEmpty";
    private static final String PAGE = "page";
    private static final String EXAM_LIST = "ExamList";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String USER = "user";
    private static final String CON_LIST_EMPTY = "con.list.empty";

    private MarkService markService = new MarkService();

    private static final Logger LOGGER = LogManager.getLogger(StudentExamListCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        String pageNumber;
        int studentId;
        int noOfRecords = 0;
        List<ExamResult> list = null;
        int page = 1;
        int recordsPerPage = 3;

        try {
            User user = (User) requestContent.getSessionAttribute(USER);
            studentId = user.getUserID();

            if (markService.allStudentMarks(studentId) == 0) {
                requestContent.setAttribute(LIST_EMPTY, bundle.getString(CON_LIST_EMPTY));
            }
            page = Integer.parseInt(requestContent.getParameter(PAGE));

            list = markService.numberOfStudentMarks(studentId, (page - 1), recordsPerPage);
            noOfRecords = markService.allStudentMarks(studentId);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter page is found ", e);
            page = 1;
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            pageNumber = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        requestContent.setAttribute(EXAM_LIST, list);
        requestContent.setAttribute(NO_OF_PAGES, noOfPages);
        requestContent.setAttribute(CURRENT_PAGE, page);

        pageNumber = ConfigurationManager.getProperty(MARK_RESULTS_PATH);

        return pageNumber;
    }
}
