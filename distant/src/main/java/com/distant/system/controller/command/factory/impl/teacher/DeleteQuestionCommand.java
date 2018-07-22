package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.QuestionService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class DeleteQuestionCommand implements ActionCommand {

    private static final String QUESTION_ID_PARAM = "questionId";
    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String MSG_DELETE_QUESTION = "msgdeletequestion";
    private static final String CON_DELETE_QUESTION = "con.delete.question";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private QuestionService questionService = new QuestionService();

    private static final Logger LOGGER = LogManager.getLogger(DeleteQuestionCommand.class);


    @Override
    public String executePost(SessionRequestContent requestContent) {
        String page;
        int questionId;
        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);

        try {
            questionId = Integer.parseInt(requestContent.getParameter(QUESTION_ID_PARAM));
            questionService.deleteQuestion(questionId);
            requestContent.setAttribute(MSG_DELETE_QUESTION, bundle.getString(CON_DELETE_QUESTION));
            page = ConfigurationManager.getProperty(ACTION_COMPLETED);
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter is not found");
            e.printStackTrace();
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
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
