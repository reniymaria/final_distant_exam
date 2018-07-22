package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ValidationException;

import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class UpdateQuestionCommand implements ActionCommand {

    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String CORRECT_ANSWER_PARAM = "correctAnswer";
    private static final String TEACHER_EDIT_QUESTION_PATH = "path.page.teacher.edit.question";
    private static final String MSGEDITQUESTION_ATTR = "msgeditquestion";
    private static final String CON_MSGEDITQUESTION = "con.msgeditquestion";
    private static final String QUESTION_ID_PARAM = "questionId";
    private static final String QUESTION_ATTR = "question";
    private static final String QUESTION_PARAM = "question";
    private static final String ANSWER_1_PARAM = "answer1";
    private static final String ANSWER_2_PARAM = "answer2";
    private static final String ANSWER_3_PARAM = "answer3";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String SUBJECT_ATTR = "subject";
    private static final String LANG_ATTR = "lang";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String ERR_MSG = "errMsg";


    private QuestionService questionService = new QuestionService();
    private SubjectService subjectService = new SubjectService();
    private LanguageService languageService = new LanguageService();

    private static final Logger LOGGER = LogManager.getLogger(UpdateQuestionCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        String page;

        int questionId;
        String question;
        String answer1;
        String answer2;
        String answer3;
        int correctAnswer;

        try {

            question = requestContent.getParameter(QUESTION_PARAM);
            answer1 = requestContent.getParameter(ANSWER_1_PARAM);
            answer2 = requestContent.getParameter(ANSWER_2_PARAM);
            answer3 = requestContent.getParameter(ANSWER_3_PARAM);
            correctAnswer = Integer.parseInt(requestContent.getParameter(CORRECT_ANSWER_PARAM));

            Question questionDB = (Question) requestContent.getSessionAttribute(QUESTION_ATTR);
            questionId = questionDB.getQuestionId();

            Question questionFromWeb = new Question(questionId, question, answer1, answer2, answer3, correctAnswer, questionDB.getSubjectId(), questionDB.getLanguageId());

            questionService.update(questionFromWeb);

            requestContent.setAttribute(MSGEDITQUESTION_ATTR, bundle.getString(CON_MSGEDITQUESTION));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);

        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            requestContent.setAttribute(ERR_MSG, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } catch (ValidationException e) {
            LOGGER.warn("Validation exception", e);
            requestContent.setAttribute(ERR_MSG, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return page;
    }


    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String page;
        Question question;
        int questionId;

        try {

            questionId = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
            question = questionService.find(questionId);
            requestContent.setSessionAttribute(QUESTION_ATTR, question);
            page = editQuestion(question, requestContent);

        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            Question question1 = getSessionQuestion(requestContent);
            page = editQuestion(question1, requestContent);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return page;
    }

    private Question getSessionQuestion(SessionRequestContent requestContent) {
        Question questionDB = null;

        try {
            questionDB = (Question) requestContent.getSessionAttribute(QUESTION_ATTR);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.error("No such parameter", e);
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
            LOGGER.error("Service exception", e);
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
