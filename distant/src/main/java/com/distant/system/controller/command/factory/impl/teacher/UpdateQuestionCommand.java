package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.QuestionService;
import com.distant.system.service.ServiceFactory;
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
    private static final String QUESTION_ATTR = "question";
    private static final String QUESTION_PARAM = "question";
    private static final String ANSWER_1_PARAM = "answer1";
    private static final String ANSWER_2_PARAM = "answer2";
    private static final String ANSWER_3_PARAM = "answer3";
    private static final String CON_FIELD_EMPTY = "con.field.empty";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String ERR_MSG = "errMsg";


    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    private static final Logger logger = LogManager.getLogger(UpdateQuestionCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

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
            logger.warn("No such parameter found", e);
            requestContent.setAttribute(ERR_MSG, bundle.getString(CON_FIELD_EMPTY));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } catch (ValidationException e) {
            logger.warn("Validation exception", e);
            requestContent.setAttribute(ERR_MSG, bundle.getString(e.getMessage()));
            page = ConfigurationManager.getProperty(TEACHER_EDIT_QUESTION_PATH);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return page;
    }


}
