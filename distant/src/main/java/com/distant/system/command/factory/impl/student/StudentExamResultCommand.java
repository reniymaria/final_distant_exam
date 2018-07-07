package com.distant.system.command.factory.impl.student;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.util.ConfigurationManager;

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

    private MarkService markService = new MarkService();


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
            } catch (DaoException e) {
                e.printStackTrace();
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
