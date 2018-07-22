package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
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


    private QuestionService questionService = new QuestionService();
    private LanguageService languageService = new LanguageService();

    private static final Logger LOGGER = LogManager.getLogger(ListQuestionsCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {

        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        int subjectId;
        String lang;
        int langId;
        int page;
        int noOfRecords;
        int recordsPerPage = 3;
        List<Question> list;

        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_PARAM));
            lang = requestContent.getParameter(LANG_PARAM);
            langId = languageService.findId(lang);
            requestContent.setSessionAttribute(SUBJECT_ID_ATTR, subjectId);
            requestContent.setSessionAttribute(LANG_ID_ATTR, langId);
            requestContent.setSessionAttribute(LANG_PARAM, lang);
            page = Integer.parseInt(requestContent.getParameter(PAGE_PARAM));

            list = questionService.numberOfQuestions(subjectId, langId, (page - 1),
                    recordsPerPage);

            noOfRecords = questionService.allQuestions(subjectId, langId);

            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            requestContent.setAttribute(QUESTION_LIST_ATTR, list);
            requestContent.setAttribute(NO_OF_PAGES_ATTR, noOfPages);
            requestContent.setAttribute(CURRENT_PAGE_ATTR, page);

            pageString = ConfigurationManager.getProperty(QUESTION_LIST_PATH);

        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ValidationException e) {
            LOGGER.warn("Validation exception");
            requestContent.setAttribute(LIST_EMPTY_ATTR, bundle.getString(e.getMessage()));
            pageString = ConfigurationManager.getProperty(QUESTION_LIST_PATH);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter exception");
            pageString = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return pageString;
    }
}
