package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.CommandUtil;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.entity.User;
import com.distant.system.service.MarkService;
import com.distant.system.service.ServiceFactory;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class StudentExamResultCommand implements ActionCommand {

    private static final String EXAM_RESULT_PATH_PAGE = "path.page.result.exam";
    private static final String EXAM_QUESTIONS_ATTR = "examQuestions";
    private static final String RESULT_FAILED_ATTR = "resultFailed";
    private static final String RESULT_ATTR = "result";
    private static final String CON_EXAMRESULT_STUDENT = "con.examresult.student";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";
    private static final String USER = "user";

    private MarkService markService = ServiceFactory.getInstance().getMarkService();

    private static final Logger logger = LogManager.getLogger(StudentExamResultCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;
        ResourceBundle bundle = CommandUtil.takeBundle(requestContent);
        List<Question> fiveQuestion;
        int studentId;
        int subjectId = 0;
        int examResult = 0;


        try {
            fiveQuestion = (List<Question>) requestContent.getSessionAttribute(EXAM_QUESTIONS_ATTR);
            User user = (User) requestContent.getSessionAttribute(USER);
            studentId = user.getUserID();

            for (Question question : fiveQuestion) {
                subjectId = question.getSubjectId();
                String param = String.valueOf(question.getQuestionId());

                int correctAnswer = Integer.parseInt(requestContent.getParameter(param));

                if (question.getCorrectAnswer() == correctAnswer) {
                    examResult++;
                }
            }

            requestContent.setAttribute(RESULT_ATTR, bundle.getString(CON_EXAMRESULT_STUDENT) + examResult);

            markService.addMark(examResult, studentId, subjectId);
            requestContent.removeSessionAttribute(EXAM_QUESTIONS_ATTR);
            page = ConfigurationManager.getProperty(EXAM_RESULT_PATH_PAGE);

        } catch (ValidationException e) {
            requestContent.setAttribute(RESULT_FAILED_ATTR, bundle.getString(e.getMessage()) + examResult);
            requestContent.removeSessionAttribute(EXAM_QUESTIONS_ATTR);
            page = ConfigurationManager.getProperty(EXAM_RESULT_PATH_PAGE);
        } catch (NoSuchRequestParameterException e) {
            logger.warn("No such parameter", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return page;
    }

}
