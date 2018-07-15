package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.QuestionService;
import com.distant.system.controller.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteQuestionCommand implements ActionCommand {

    private static final String QUESTION_ID_PARAM = "questionId";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String LANGUAGE = "language";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String MSG_DELETE_QUESTION = "msgdeletequestion";
    private static final String CON_DELETE_QUESTION = "con.delete.question";

    private QuestionService questionService = new QuestionService();

    private static final Logger LOGGER = LogManager.getLogger(DeleteQuestionCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;
        Locale locale = null;
        int questionId = 0;

        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            questionId = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found");
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        try {
            questionService.deleteQuestion(questionId);
        } catch (DaoException e) {
            LOGGER.error("Dao exception", e);
            e.printStackTrace();
        }
        requestContent.setAttribute(MSG_DELETE_QUESTION, bundle.getString(CON_DELETE_QUESTION));
        page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
