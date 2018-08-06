package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
import com.distant.system.service.ServiceFactory;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class ListQuestionsCommand implements ActionCommand {

    private static final String QUESTION_LIST_PATH = "path.page.question.list";
    private static final String SUBJECT_ID_PARAM = "subjectId";
    private static final String LANG_PARAM = "lang";
    private static final String PAGE_PARAM = "page";
    private static final String LIST_EMPTY_ATTR = "listEmpty";
    private static final String QUESTION_LIST_ATTR = "QuestionList";
    private static final String NO_OF_PAGES_ATTR = "noOfPages";
    private static final String CURRENT_PAGE_ATTR = "currentPage";
    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String LANG_ID_ATTR = "langId";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private LanguageService languageService = ServiceFactory.getInstance().getLanguageService();

    private static final Logger logger = LogManager.getLogger(ListQuestionsCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        String pageString;
        int page;
        int subjectId;
        String lang;
        int langId;

        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_PARAM));
            lang = requestContent.getParameter(LANG_PARAM);
            langId = languageService.findId(lang);
            requestContent.setSessionAttribute(SUBJECT_ID_ATTR, subjectId);
            requestContent.setSessionAttribute(LANG_ID_ATTR, langId);
            requestContent.setSessionAttribute(LANG_PARAM, lang);

            page = Integer.parseInt(requestContent.getParameter(PAGE_PARAM));
            pageString = createList(page, requestContent);
        } catch (NoSuchRequestParameterException e) {
            logger.warn("No such parameter exception for page");
            page = 1;
            pageString = createList(page, requestContent);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return pageString;
    }

    private String createList(int page, SessionRequestContent requestContent) {

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        int noOfRecords;
        int recordsPerPage = 3;
        List<Question> list;
        String pageString;
        int subjectId;
        int langId;

        try {
            subjectId = (int) requestContent.getSessionAttribute(SUBJECT_ID_ATTR);
            langId = (int) requestContent.getSessionAttribute(LANG_ID_ATTR);
            list = questionService.numberOfQuestions(subjectId, langId, (page - 1),
                    recordsPerPage);
            noOfRecords = questionService.allQuestions(subjectId, langId);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            requestContent.setAttribute(QUESTION_LIST_ATTR, list);
            requestContent.setAttribute(NO_OF_PAGES_ATTR, noOfPages);
            requestContent.setAttribute(CURRENT_PAGE_ATTR, page);
            pageString = ConfigurationManager.getProperty(QUESTION_LIST_PATH);
        } catch (NoSuchRequestParameterException e) {
            logger.warn("No such parameter exception");
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ValidationException e) {
            logger.warn("Validation exception");
            requestContent.setAttribute(LIST_EMPTY_ATTR, bundle.getString(e.getMessage()));
            pageString = ConfigurationManager.getProperty(QUESTION_LIST_PATH);
        }

        return pageString;
    }
}
