package com.distant.system.command.factory.impl.teacher;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.QuestionService;
import com.distant.system.util.ConfigurationManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class UpdateQuestionCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String CORRECT_ANSWER_PARAM = "correctAnswer";
    private static final String TEACHER_EDIT_QUESTION_PATH = "path.page.teacher.edit.question";
    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String LANG_ID_ATTR = "langId";
    private static final String MSGEDITQUESTION_ATTR = "msgeditquestion";
    private static final String CON_MSGEDITQUESTION = "con.msgeditquestion";
    private static final String QUESTION_ID_PARAM = "questionId";
    private static final String QUESTION_PARAM = "question";
    private static final String ANSWER_1_PARAM = "answer1";
    private static final String ANSWER_2_PARAM = "answer2";
    private static final String ANSWER_3_PARAM = "answer3";
    private static final String SUBJECT_ID_PARAM = "subjectId";
    private static final String LANG_ID_PARAM = "langId";
    private static final String QUESTION_ATTR = "question";


    private QuestionService questionService = new QuestionService();

    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;

        Locale locale = null;
        int questionId = 0;
        String question = null;
        int correctAnswer = 0;
        String answer1 = null;
        String answer2 = null;
        String answer3 = null;
        int subjectId = 0;
        int langId = 0;

        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            questionId = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
            question = requestContent.getParameter(QUESTION_PARAM);
            correctAnswer = Integer.parseInt(requestContent.getParameter(CORRECT_ANSWER_PARAM));
            answer1 = requestContent.getParameter(ANSWER_1_PARAM);
            answer2 = requestContent.getParameter(ANSWER_2_PARAM);
            answer3 = requestContent.getParameter(ANSWER_3_PARAM);
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID_PARAM));
            langId = Integer.parseInt(requestContent.getParameter(LANG_ID_PARAM));

        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        Question questionFromWeb = new Question(questionId, question, answer1, answer2, answer3, correctAnswer);
        try {
            questionService.update(questionFromWeb);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        requestContent.setAttribute(SUBJECT_ID_ATTR, subjectId);
        requestContent.setAttribute(LANG_ID_ATTR, langId);
        requestContent.setAttribute(MSGEDITQUESTION_ATTR, bundle.getString(CON_MSGEDITQUESTION));

        page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String page;

        int id = 0;
        try {
            id = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        Question question = null;
        try {
            question = questionService.find(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        requestContent.setAttribute(QUESTION_ATTR, question);
        page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        return page;
    }
}
