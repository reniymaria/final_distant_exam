package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.QuestionService;
import com.distant.system.controller.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ListQuestionsCommand implements ActionCommand {

    private static final String QUESTION_LIST_PATH = "path.page.question.list";
    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String SUBJECT_ID_PARAM = "subjectId";
    private static final String LANG_ID_PARAM = "langId";
    private static final String PAGE_PARAM = "page";
    private static final String LIST_EMPTY_ATTR = "listEmpty";
    private static final String CON_LIST_EMPTY = "con.list.empty";
    private static final String QUESTION_LIST_ATTR = "QuestionList";
    private static final String NO_OF_PAGES_ATTR = "noOfPages";
    private static final String CURRENT_PAGE_ATTR = "currentPage";
    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String LANG_ID_ATTR = "langId";


    private QuestionService questionService = new QuestionService();

    private static final Logger LOGGER = LogManager.getLogger(ListQuestionsCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {

        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;

        int subjectId = 0;
        int langId = 0;
        Locale locale = null;
        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_PARAM));
            langId = Integer.parseInt(requestContent.getParameter(LANG_ID_PARAM));
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);


        int page = 1;
        int recordsPerPage = 3;
        try {
            if (requestContent.getParameter(PAGE_PARAM) != null) {
                page = Integer.parseInt(requestContent.getParameter(PAGE_PARAM));
            }
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter page", e);
            e.printStackTrace();
        }

        List<Question> list = null;
        try {
            list = questionService.numberOfQuestions(subjectId, langId, (page - 1),
                    recordsPerPage);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = questionService.allQuestions(subjectId, langId);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        requestContent.setAttribute(QUESTION_LIST_ATTR, list);
        requestContent.setAttribute(NO_OF_PAGES_ATTR, noOfPages);
        requestContent.setAttribute(CURRENT_PAGE_ATTR, page);

        requestContent.setSessionAttribute(SUBJECT_ID_ATTR, subjectId);
        requestContent.setSessionAttribute(LANG_ID_ATTR, langId);

        if (noOfRecords == 0) {
            requestContent.setAttribute(LIST_EMPTY_ATTR, bundle.getString(CON_LIST_EMPTY));
        }

        pageString = ConfigurationManager.getProperty(QUESTION_LIST_PATH);
        return pageString;
    }
}
