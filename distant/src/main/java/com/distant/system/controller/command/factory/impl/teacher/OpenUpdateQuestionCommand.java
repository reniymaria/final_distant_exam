package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.entity.Question;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
import com.distant.system.service.ServiceFactory;
import com.distant.system.service.SubjectService;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenUpdateQuestionCommand implements ActionCommand {

    private static final String TEACHER_EDIT_QUESTION_PATH = "path.page.teacher.edit.question";
    private static final String QUESTION_ID_PARAM = "questionId";
    private static final String QUESTION_ATTR = "question";
    private static final String SUBJECT_ATTR = "subject";
    private static final String LANG_ATTR = "lang";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();
    private LanguageService languageService = ServiceFactory.getInstance().getLanguageService();

    private static final Logger logger = LogManager.getLogger(OpenUpdateQuestionCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;
        Question question;
        int questionId;

        try {

            questionId = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
            question = questionService.find(questionId);
            requestContent.setSessionAttribute(QUESTION_ATTR, question);
            page = editQuestion(question, requestContent);

        } catch (NoSuchRequestParameterException e) {
            logger.warn("No such parameter found", e);
            Question question1 = getSessionQuestion(requestContent);
            page = editQuestion(question1, requestContent);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return page;
    }

    private Question getSessionQuestion(SessionRequestContent requestContent) {
        Question questionDB = null;

        try {
            questionDB = (Question) requestContent.getSessionAttribute(QUESTION_ATTR);
        } catch (NoSuchRequestParameterException e) {
            logger.error("No such parameter", e);
        }
        return questionDB;
    }

    private String editQuestion(Question question, SessionRequestContent requestContent) {

        String page = null;
        String subject = null;
        String lang = null;


        try {
            subject = subjectService.getSubjectById(question.getSubjectId());
            lang = languageService.getLanguageById(question.getLanguageId());
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        requestContent.setAttribute(SUBJECT_ATTR, subject);
        requestContent.setAttribute(LANG_ATTR, lang);
        requestContent.setAttribute(QUESTION_ATTR, question);
        if (page == null) {
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        }
        return page;
    }

}
