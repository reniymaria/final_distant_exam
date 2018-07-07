package com.distant.system.command.factory.impl.teacher;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
import com.distant.system.service.SubjectService;
import com.distant.system.util.ConfigurationManager;
import com.distant.system.util.FieldsUtil;

import java.util.Locale;
import java.util.ResourceBundle;

public class AddQuestionCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String TEACHER_ADD_QUESTION_PATH_PAGE = "path.page.teacher.add.question";
    private static final String ACTION_COMPLETED = "path.page.action.completed";

    private QuestionService questionService = new QuestionService();
    private SubjectService subjectService = new SubjectService();
    private LanguageService languageService = new LanguageService();


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page = null;

        String subject = null;
        String lang = null;

        Locale locale = null;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
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
            subjectId = Integer.parseInt(requestContent.getParameter("subjectId"));
            langId = Integer.parseInt(requestContent.getParameter("langId"));
            question = requestContent.getParameter("question");
            answer1 = requestContent.getParameter("answer1");
            answer2 = requestContent.getParameter("answer2");
            answer3 = requestContent.getParameter("answer3");
            correctAnswer = Integer.parseInt(requestContent.getParameter("correctAnswer"));

        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }

        try {
            subject = subjectService.getSubjectById(subjectId);
            lang = languageService.getLanguageById(langId);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        requestContent.setAttribute("subjectId", subjectId);
        requestContent.setAttribute("langId", langId);
        requestContent.setAttribute("subject", subject);
        requestContent.setAttribute("lang", lang);

        if (FieldsUtil.isEmpty(question)) {
            requestContent.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(answer1)) {
            requestContent.setAttribute("emptyMess2", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(answer2)) {
            requestContent.setAttribute("emptyMess3", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (FieldsUtil.isEmpty(answer3)) {
            requestContent.setAttribute("emptyMess4", bundle.getString("con.field.empty"));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (!FieldsUtil.isNumberCorrectAnswer(correctAnswer)) {
            requestContent.setAttribute("answerIncorrect", bundle.getString("con.answer.invalid"));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } else if (page == null) {
            Question questionFromWeb = new Question(question, answer1, answer2, answer3, correctAnswer, subjectId, langId);

            try {
                questionService.add(questionFromWeb);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            requestContent.setAttribute("msgaddquestion", bundle.getString("con.msgaddquestion"));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String page;

        int subjectId = 0;
        int langId = 0;
        try {
            subjectId = Integer.parseInt(requestContent.getParameter("subjectId"));
            langId = Integer.parseInt(requestContent.getParameter("langId"));
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }

        String subject = null;
        String lang = null;

        try {
            subject = subjectService.getSubjectById(subjectId);
            lang = languageService.getLanguageById(langId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        requestContent.setAttribute("subjectId", subjectId);
        requestContent.setAttribute("langId", langId);
        requestContent.setAttribute("subject", subject);
        requestContent.setAttribute("lang", lang);

        page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);

        return page;
    }
}
