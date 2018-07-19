package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.util.Validation;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class AddQuestionCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String TEACHER_ADD_QUESTION_PATH_PAGE = "path.page.teacher.add.question";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String SUBJECT_ID = "subjectId";
    private static final String LANG_ID = "langId";
    private static final String QUESTION = "question";
    private static final String ANSWER_1 = "answer1";
    private static final String ANSWER_2 = "answer2";
    private static final String ANSWER_3 = "answer3";
    private static final String CORRECT_ANSWER = "correctAnswer";
    private static final String SUBJECT = "subject";
    private static final String LANG = "lang";
    private static final String EMPTY_MESS_1 = "emptyMess1";
    private static final String EMPTY_MESS_2 = "emptyMess2";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String CON_ANSWER_INVALID = "con.answer.invalid";
    private static final String EMPTY_MESS_3 = "emptyMess3";
    private static final String EMPTY_MESS_4 = "emptyMess4";
    private static final String ANSWER_INCORRECT = "answerIncorrect";
    private static final String MSGADDQUESTION = "msgaddquestion";
    private static final String CON_MSGADDQUESTION = "con.msgaddquestion";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private QuestionService questionService = new QuestionService();
    private SubjectService subjectService = new SubjectService();
    private LanguageService languageService = new LanguageService();

    private static final Logger LOGGER = LogManager.getLogger(AddQuestionCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        String subject = null;
        String lang = null;

        Locale locale = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        int subjectId = 0;
        int langId = 0;
        String question = null;
        String answer1 = null;
        String answer2 = null;
        String answer3 = null;
        int correctAnswer = 0;
        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID));
            langId = Integer.parseInt(requestContent.getParameter(LANG_ID));
            question = requestContent.getParameter(QUESTION);
            answer1 = requestContent.getParameter(ANSWER_1);
            answer2 = requestContent.getParameter(ANSWER_2);
            answer3 = requestContent.getParameter(ANSWER_3);
            correctAnswer = Integer.parseInt(requestContent.getParameter(CORRECT_ANSWER));

        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
            e.printStackTrace();
        }

        try {
            subject = subjectService.getSubjectById(subjectId);

        lang = languageService.getLanguageById(langId);

        requestContent.setAttribute(SUBJECT_ID, subjectId);
        requestContent.setAttribute(LANG_ID, langId);
        requestContent.setAttribute(SUBJECT, subject);
        requestContent.setAttribute(LANG, lang);

        if (Validation.isEmpty(question)) {
            requestContent.setAttribute(EMPTY_MESS_1, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (Validation.isEmpty(answer1)) {
            requestContent.setAttribute(EMPTY_MESS_2, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (Validation.isEmpty(answer2)) {
            requestContent.setAttribute(EMPTY_MESS_3, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (Validation.isEmpty(answer3)) {
            requestContent.setAttribute(EMPTY_MESS_4, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (!Validation.isNumberCorrectAnswer(correctAnswer)) {
            requestContent.setAttribute(ANSWER_INCORRECT, bundle.getString(CON_ANSWER_INVALID));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (page == null) {
            Question questionFromWeb = new Question(question, answer1, answer2, answer3, correctAnswer, subjectId, langId);

            questionService.add(questionFromWeb);

            requestContent.setAttribute(MSGADDQUESTION, bundle.getString(CON_MSGADDQUESTION));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        }
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            return page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String page;

        int subjectId = 0;
        int langId = 0;
        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID));
            langId = Integer.parseInt(requestContent.getParameter(LANG_ID));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found", e);
        }

        String subject = null;
        String lang = null;

        try {
            subject = subjectService.getSubjectById(subjectId);

        lang = languageService.getLanguageById(langId);
        } catch (ServiceException e) {
            LOGGER.error("Service exception", e);
            return page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        requestContent.setAttribute(SUBJECT_ID, subjectId);
        requestContent.setAttribute(LANG_ID, langId);
        requestContent.setAttribute(SUBJECT, subject);
        requestContent.setAttribute(LANG, lang);

        page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);

        return page;
    }
}
