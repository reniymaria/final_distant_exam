package com.distant.system.controller.command;

import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.MarkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentExamResultCommand extends AbstractCommand {

    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/examresult").forward(request, response);
    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        MarkService markService = new MarkService();

        HttpSession session = request.getSession(true);
        List<Question> fiveQuestion = (List<Question>) session.getAttribute("examQuestions");
        int studentId = (int) session.getAttribute("studentID");
        int subjectId = 0;

        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        int examResult = 0;
        for (Question question : fiveQuestion) {
            subjectId = question.getSubjectId();
            String param = String.valueOf(question.getQuestionId());
            int correctAnswer = Integer.parseInt(request.getParameter(param));
            if (question.getCorrectAnswer() == correctAnswer) {
                examResult++;
            }
        }

        if (examResult == 0) {
            request.setAttribute("resultFailed", bundle.getString("con.exam.result.failed") + examResult);
            session.removeAttribute("examQuestions");
            request.getRequestDispatcher("/exam_result").forward(request, response);
        } else if (examResult > 0) {
            request.setAttribute("result", bundle.getString("con.examresult.student") + examResult);

            try {
                markService.addMark(examResult, studentId, subjectId);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            session.removeAttribute("examQuestions");
            request.getRequestDispatcher("/exam_result").forward(request, response);
        }

    }
}
