package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;

import com.distant.system.dao.util.DaoUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.QuestionService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import java.util.ResourceBundle;

public class StudentExamCommand implements ActionCommand {

    private static final String EXAM_PATH_PAGE = "path.page.exam";
    private static final String QUESTION_NOT_ENOUGH_ATTR = "questionNotEnough";
    private static final String EXAM_QUESTIONS_ATTR = "examQuestions";
    private static final String EXAMLANG_PARAM = "examlang";
    private static final String SUBJECT_PARAM = "subject";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String CON_PARAM_ERROR = "con.param.error";

    private QuestionService questionService = new QuestionService();

    private static final Logger LOGGER = LogManager.getLogger(StudentExamCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {

        String page;
        String subject;
        String examlang;

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);
        List<Question> examQuestion;

        try {
            examlang = requestContent.getParameter(EXAMLANG_PARAM);
            subject = requestContent.getParameter(SUBJECT_PARAM);
            examQuestion = DaoUtil.getRandomFive(questionService.getQuestions(subject, examlang));
            requestContent.setSessionAttribute(EXAM_QUESTIONS_ATTR, examQuestion);
            page = ConfigurationManager.getProperty(EXAM_PATH_PAGE);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            requestContent.setAttribute(QUESTION_NOT_ENOUGH_ATTR, bundle.getString(CON_PARAM_ERROR));
            page = ConfigurationManager.getProperty(EXAM_PATH_PAGE);
        } catch (ValidationException e) {
            LOGGER.warn("Validation exception");
            requestContent.setAttribute(QUESTION_NOT_ENOUGH_ATTR, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(EXAM_PATH_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
