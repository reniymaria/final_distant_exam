package com.distant.system.controller.command.factory.impl.teacher;

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

public class ExamResultsCommand implements ActionCommand {

    private static final String EXAM_RESULTS_PATH_PAGE = "path.page.teacher.exam.results";
    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String LIST_EMPTY_ATTR = "listEmpty";
    private static final String CON_LIST_EMPTY = "con.list.empty";
    private static final String PAGE_PARAM = "page";
    private static final String MARK_LIST_ATTR = "MarkList";
    private static final String NO_OF_PAGES_ATTR = "noOfPages";
    private static final String CURRENT_PAGE_ATTR = "currentPage";

    private MarkService markService = new MarkService();

    private static final Logger LOGGER = LogManager.getLogger(ExamResultsCommand.class);

    
    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;

        Locale locale = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        try {
            if (markService.allMarks() == 0) {
                requestContent.setAttribute(LIST_EMPTY_ATTR, bundle.getString(CON_LIST_EMPTY));
            }
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }

        int page = 1;
        int recordsPerPage = 3;
        try {
            if (requestContent.getParameter(PAGE_PARAM) != null) {
                page = Integer.parseInt(requestContent.getParameter(PAGE_PARAM));
            }
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }

        List<ExamResult> listExam = null;
        try {
            listExam = markService.numberOfMarks((page - 1),
                    recordsPerPage);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = markService.allMarks();
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        requestContent.setAttribute(MARK_LIST_ATTR, listExam);
        requestContent.setAttribute(NO_OF_PAGES_ATTR, noOfPages);
        requestContent.setAttribute(CURRENT_PAGE_ATTR, page);

        pageString = ConfigurationManager.getProperty(EXAM_RESULTS_PATH_PAGE);
        return pageString;
    }
}
