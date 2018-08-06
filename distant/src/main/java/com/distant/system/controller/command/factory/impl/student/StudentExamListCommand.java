package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.User;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.service.ServiceFactory;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
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

    private MarkService markService = ServiceFactory.getInstance().getMarkService();

    private static final Logger logger = LogManager.getLogger(StudentExamListCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        String pageString;
        int page;

        try {
            page = Integer.parseInt(requestContent.getParameter(PAGE));
            pageString = createListMarks(page, requestContent);
        } catch (NoSuchRequestParameterException e) {
            logger.warn("No such parameter page is found", e);
            page = 1;
            pageString = createListMarks(page, requestContent);
        }
        return pageString;
    }

    private String createListMarks(int page, SessionRequestContent requestContent) {

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        String pageString;
        int studentId;
        int noOfRecords;
        List<ExamResult> list;
        int recordsPerPage = 3;

        try {
            User user = (User) requestContent.getSessionAttribute(USER);
            studentId = user.getUserID();

            list = markService.numberOfStudentMarks(studentId, (page - 1), recordsPerPage);
            noOfRecords = markService.allStudentMarks(studentId);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            requestContent.setAttribute(EXAM_LIST, list);
            requestContent.setAttribute(NO_OF_PAGES, noOfPages);
            requestContent.setAttribute(CURRENT_PAGE, page);

            pageString = ConfigurationManager.getProperty(MARK_RESULTS_PATH);

        } catch (ValidationException e) {
            requestContent.setAttribute(LIST_EMPTY, bundle.getString(e.getMessage()));
            pageString = ConfigurationManager.getProperty(MARK_RESULTS_PATH);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (NoSuchRequestParameterException e) {
            logger.error("Parameter exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return pageString;
    }
}
