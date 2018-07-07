package com.distant.system.command.factory.impl.teacher;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.util.ConfigurationManager;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExamResultsCommand implements ActionCommand {

    private static final String EXAM_RESULTS_PATH_PAGE = "path.page.teacher.exam.results";
    public static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String LIST_EMPTY_ATTR = "listEmpty";
    private static final String CON_LIST_EMPTY = "con.list.empty";
    private static final String PAGE_PARAM = "page";
    private static final String SUBJECT_ID_PARAM = "subjectID";
    private static final String USER_ID_PARAM = "userID";
    private static final String ERR_MSG_ATTR = "errMsg";
    private static final String CON_VALUE_IS_DELETED = "con.value.is.deleted";
    private static final String MARK_LIST_ATTR = "MarkList";
    private static final String NO_OF_PAGES_ATTR = "noOfPages";
    private static final String CURRENT_PAGE_ATTR = "currentPage";

    private MarkService markService = new MarkService();


    @Override
    public String executePost(SessionRequestContent requestContent) {

        Locale locale = null;


        int subjectID = 0;
        int userID = 0;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            subjectID = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_PARAM));
            userID = Integer.parseInt(requestContent.getParameter(USER_ID_PARAM));
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        try {
            markService.deleteMark(userID, subjectID);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        requestContent.setAttribute(ERR_MSG_ATTR, bundle.getString(CON_VALUE_IS_DELETED));
        executeGet(requestContent);

        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;

        Locale locale = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        try {
            if (markService.allMarks() == 0) {
                requestContent.setAttribute(LIST_EMPTY_ATTR, bundle.getString(CON_LIST_EMPTY));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        int page = 1;
        int recordsPerPage = 3;
        try {
            if (requestContent.getParameter(PAGE_PARAM) != null) {
                page = Integer.parseInt(requestContent.getParameter(PAGE_PARAM));
            }
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }

        List<ExamResult> listExam = null;
        try {
            listExam = markService.numberOfMarks((page - 1),
                    recordsPerPage);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = markService.allMarks();
        } catch (DaoException e) {
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
