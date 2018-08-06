package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.QuestionService;
import com.distant.system.service.ServiceFactory;
import com.distant.system.service.SubjectService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class AddQuestionCommand implements ActionCommand {

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
    private static final String ERR_MSG = "errMsg";
    private static final String MSGADDQUESTION = "msgaddquestion";
    private static final String CON_MSGADDQUESTION = "con.msgaddquestion";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String CON_FIELD_EMPTY = "con.field.empty";

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();
    private LanguageService languageService = ServiceFactory.getInstance().getLanguageService();

    private static final Logger logger = LogManager.getLogger(AddQuestionCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        String page;
        String subject;
        String lang;
        int subjectId;
        int langId;
        String question;
        String answer1;
        String answer2;
        String answer3;
        int correctAnswer;

        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID));
            langId = Integer.parseInt(requestContent.getParameter(LANG_ID));
            question = requestContent.getParameter(QUESTION);
            answer1 = requestContent.getParameter(ANSWER_1);
            answer2 = requestContent.getParameter(ANSWER_2);
            answer3 = requestContent.getParameter(ANSWER_3);
            correctAnswer = Integer.parseInt(requestContent.getParameter(CORRECT_ANSWER));


            subject = subjectService.getSubjectById(subjectId);
            lang = languageService.getLanguageById(langId);

            requestContent.setAttribute(SUBJECT_ID, subjectId);
            requestContent.setAttribute(LANG_ID, langId);
            requestContent.setAttribute(SUBJECT, subject);
            requestContent.setAttribute(LANG, lang);


            Question questionFromWeb = new Question(question, answer1, answer2, answer3, correctAnswer, subjectId, langId);

            questionService.add(questionFromWeb);

            requestContent.setAttribute(MSGADDQUESTION, bundle.getString(CON_MSGADDQUESTION));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);

        } catch (NoSuchRequestParameterException e) {
            logger.warn("Parameter is not found", e);
            requestContent.setAttribute(ERR_MSG, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } catch (ValidationException e) {
            logger.warn("Validation exception", e);
            requestContent.setAttribute(ERR_MSG, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }

        return page;
    }

}
