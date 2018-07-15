package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.dao.util.DaoUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.QuestionService;
import com.distant.system.controller.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentExamCommand implements ActionCommand {

    private static final String I18N_CONTENT = "i18n.content";
    private static final String LANGUAGE = "language";
    private static final String EXAM_PATH_PAGE = "path.page.exam";
    private static final String QUESTION_NOT_ENOUGH_ATTR = "questionNotEnough";
    private static final String CON_LIST_QUESTION_ERROR = "con.list.question.error";
    private static final String EXAM_QUESTIONS_ATTR = "examQuestions";
    private static final String EXAMLANG_PARAM = "examlang";
    private static final String SUBJECT_PARAM = "subject";

    private QuestionService questionService = new QuestionService();

    private static final Logger LOGGER = LogManager.getLogger(StudentExamCommand.class);

    
    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;

        Locale locale = null;
        String subject = null;
        String examlang = null;

        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            examlang = requestContent.getParameter(EXAMLANG_PARAM);
            subject = requestContent.getParameter(SUBJECT_PARAM);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);


        List<Question> examQuestion = new ArrayList<>();
        try {
            examQuestion = DaoUtil.getRandomFive(questionService.getQuestions(subject, examlang));
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }

        if (examQuestion.size() < 5) {
            requestContent.setAttribute(QUESTION_NOT_ENOUGH_ATTR, bundle.getString(CON_LIST_QUESTION_ERROR));
            page = ConfigurationManager.getProperty(EXAM_PATH_PAGE);
            return page;
        }

        requestContent.setSessionAttribute(EXAM_QUESTIONS_ATTR, examQuestion);
        page = ConfigurationManager.getProperty(EXAM_PATH_PAGE);
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
