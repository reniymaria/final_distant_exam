package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.controller.util.FieldsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateQuestionCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
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
    private static final String EMPTY_MESS_1 = "emptyMess1";
    private static final String EMPTY_MESS_2 = "emptyMess2";
    private static final String EMPTY_MESS_3 = "emptyMess3";
    private static final String EMPTY_MESS_4 = "emptyMess4";
    private static final String EMPTY_MESS_5 = "emptyMess5";
    private static final String SUBJECT_ATTR = "subject";
    private static final String LANG_ATTR = "lang";


    private QuestionService questionService = new QuestionService();
    private SubjectService subjectService = new SubjectService();
    private LanguageService languageService = new LanguageService();

    private static final Logger LOGGER = LogManager.getLogger(UpdateQuestionCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        int questionId = 0;
        String question = null;
        String answer1 = null;
        String answer2 = null;
        String answer3 = null;
        int correctAnswer = 0;
        Locale locale = null;


        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }
        try {
            questionId = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }
        try {
            question = requestContent.getParameter(QUESTION_PARAM);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }
        try {
            answer1 = requestContent.getParameter(ANSWER_1_PARAM);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }
        try {
            answer2 = requestContent.getParameter(ANSWER_2_PARAM);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }
        try {
            answer3 = requestContent.getParameter(ANSWER_3_PARAM);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }
        try {
            correctAnswer = Integer.parseInt(requestContent.getParameter(CORRECT_ANSWER_PARAM));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }
        Question questionDB = null;
        try {
            questionDB = (Question) requestContent.getSessionAttribute(QUESTION_ATTR);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        if (FieldsUtil.isEmpty(question)) {
            requestContent.setAttribute(EMPTY_MESS_1, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } else if (FieldsUtil.isEmpty(answer1)) {
            requestContent.setAttribute(EMPTY_MESS_2, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } else if (FieldsUtil.isEmpty(answer2)) {
            requestContent.setAttribute(EMPTY_MESS_3, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } else if (FieldsUtil.isEmpty(answer3)) {
            requestContent.setAttribute(EMPTY_MESS_4, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } else if (FieldsUtil.isEmptyNum(correctAnswer)) {
            requestContent.setAttribute(EMPTY_MESS_5, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } else if (page == null) {

            Question questionFromWeb = new Question(questionId, question, answer1, answer2, answer3, correctAnswer, questionDB.getSubjectId(),questionDB.getLanguageId());

            try {
                questionService.update(questionFromWeb);
            } catch (DaoException e) {
                LOGGER.error("Dao exception", e);
                e.printStackTrace();
            }

            requestContent.setAttribute(MSGEDITQUESTION_ATTR, bundle.getString(CON_MSGEDITQUESTION));
            requestContent.setAttribute(QUESTION_ATTR, questionFromWeb);
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
            return page;
        }

        return executeGet(requestContent);
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String page;

        String subject = null;
        String lang = null;
        Question question = null;
        int id = 0;


        try {
            id = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter found", e);
            e.printStackTrace();
        }

        if (id != 0) {
            try {
                question = questionService.find(id);
            } catch (DaoException e) {
                LOGGER.error("Dao exception", e);
                e.printStackTrace();
            }
            requestContent.setSessionAttribute(QUESTION_ATTR, question);
        } else {
            try {
                question = (Question) requestContent.getSessionAttribute(QUESTION_ATTR);
            } catch (NoSuchRequestParameterException e) {
                LOGGER.warn("No such parameter found", e);
                e.printStackTrace();
            }
        }

        try {
            subject = subjectService.getSubjectById(question.getSubjectId());
            lang = languageService.getLanguageById(question.getLanguageId());
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }

        requestContent.setAttribute(SUBJECT_ATTR, subject);
        requestContent.setAttribute(LANG_ATTR, lang);
        requestContent.setAttribute(QUESTION_ATTR, question);

        page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        return page;
    }
}
