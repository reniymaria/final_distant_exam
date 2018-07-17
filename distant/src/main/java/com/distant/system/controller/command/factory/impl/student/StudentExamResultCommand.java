package com.distant.system.controller.command.factory.impl.student;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentExamResultCommand implements ActionCommand {

    private static final String EXAM_RESULT_PATH_PAGE = "path.page.result.exam";
    private static final String EXAM_QUESTIONS_ATTR = "examQuestions";
    private static final String STUDENT_ID_ATTR = "studentID";
    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String RESULT_FAILED_ATTR = "resultFailed";
    private static final String CON_EXAM_RESULT_FAILED = "con.exam.result.failed";
    private static final String RESULT_ATTR = "result";
    private static final String CON_EXAMRESULT_STUDENT = "con.examresult.student";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private MarkService markService = new MarkService();

    private static final Logger LOGGER = LogManager.getLogger(StudentExamResultCommand.class);
    

    @Override
    public String executePost(SessionRequestContent requestContent) {

        String page = null;

        List<Question> fiveQuestion = null;
        int studentId = 0;
        int subjectId = 0;
        Locale locale = null;

        try {
            fiveQuestion = (List<Question>) requestContent.getSessionAttribute(EXAM_QUESTIONS_ATTR);
            studentId = (int) requestContent.getSessionAttribute(STUDENT_ID_ATTR);
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("No such parameter", e);
            e.printStackTrace();
        }

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);

        int examResult = 0;
        for (Question question : fiveQuestion) {
            subjectId = question.getSubjectId();
            String param = String.valueOf(question.getQuestionId());
            int correctAnswer = 0;
            try {
                correctAnswer = Integer.parseInt(requestContent.getParameter(param));
            } catch (NoSuchRequestParameterException e) {
                LOGGER.warn("No such parameter", e);
                e.printStackTrace();
            }
            if (question.getCorrectAnswer() == correctAnswer) {
                examResult++;
            }
        }

        if (examResult == 0) {
            requestContent.setAttribute(RESULT_FAILED_ATTR, bundle.getString(CON_EXAM_RESULT_FAILED) + examResult);
            requestContent.removeSessionAttribute(EXAM_QUESTIONS_ATTR);
            page = ConfigurationManager.getProperty(EXAM_RESULT_PATH_PAGE);
            return page;
        } else if (examResult > 0) {
            requestContent.setAttribute(RESULT_ATTR, bundle.getString(CON_EXAMRESULT_STUDENT) + examResult);

            try {
                markService.addMark(examResult, studentId, subjectId);
            } catch (ServiceException e) {
                LOGGER.error("Service exception", e);
                return page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
            }
            requestContent.removeSessionAttribute(StudentExamResultCommand.EXAM_QUESTIONS_ATTR);
            page = ConfigurationManager.getProperty(EXAM_RESULT_PATH_PAGE);
            return page;
        }

        return page;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return null;
    }
}
