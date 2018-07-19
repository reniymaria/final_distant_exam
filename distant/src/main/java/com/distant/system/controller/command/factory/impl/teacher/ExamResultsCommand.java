package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class ExamResultsCommand implements ActionCommand {

    private static final String EXAM_RESULTS_PATH_PAGE = "path.page.teacher.exam.results";
    private static final String LIST_EMPTY_ATTR = "listEmpty";
    private static final String PAGE_PARAM = "page";
    private static final String MARK_LIST_ATTR = "MarkList";
    private static final String NO_OF_PAGES_ATTR = "noOfPages";
    private static final String CURRENT_PAGE_ATTR = "currentPage";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private MarkService markService = new MarkService();

    private static final Logger LOGGER = LogManager.getLogger(ExamResultsCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;

        int page;
        int recordsPerPage = 3;
        List<ExamResult> listExam;
        int noOfRecords;

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        try {

            page = Integer.parseInt(requestContent.getParameter(PAGE_PARAM));
            listExam = markService.numberOfMarks((page - 1), recordsPerPage);
            noOfRecords = markService.allMarks();

            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            requestContent.setAttribute(MARK_LIST_ATTR, listExam);
            requestContent.setAttribute(NO_OF_PAGES_ATTR, noOfPages);
            requestContent.setAttribute(CURRENT_PAGE_ATTR, page);

            pageString = ConfigurationManager.getProperty(EXAM_RESULTS_PATH_PAGE);

        } catch (ValidationException e) {
            requestContent.setAttribute(LIST_EMPTY_ATTR, bundle.getString(e.getMessage()));
            pageString = ConfigurationManager.getProperty(EXAM_RESULTS_PATH_PAGE);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return pageString;
    }
}
