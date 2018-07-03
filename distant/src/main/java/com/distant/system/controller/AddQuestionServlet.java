package com.distant.system.controller;

import com.distant.system.controller.util.FieldsUtil;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.distant.system.service.daoservice.LanguageService;
import com.distant.system.service.daoservice.QuestionService;
import com.distant.system.service.daoservice.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/addquestion")
public class AddQuestionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        SubjectService subjectService = new SubjectService();
        LanguageService languageService = new LanguageService();

        HttpSession session = request.getSession(true);
        Locale locale = new Locale(session.getAttribute("language").toString());
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.content", locale);

        String subject = null;
        String lang = null;

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int langId = Integer.parseInt(request.getParameter("langId"));

        try {
            subject = subjectService.getSubjectById(subjectId);
            lang = languageService.getLanguageById(langId);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        String question = request.getParameter("question");
        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");
        String answer3 = request.getParameter("answer3");
        int correctAnswer = Integer.parseInt(request.getParameter("correctAnswer"));

        request.setAttribute("subjectId", subjectId);
        request.setAttribute("langId", langId);
        request.setAttribute("subject", subject);
        request.setAttribute("lang", lang);

        if (FieldsUtil.isEmpty(question)) {
            request.setAttribute("emptyMess1", bundle.getString("con.field.empty"));
            request.getRequestDispatcher("/teacher_add_question").forward(request, response);
        } else if (FieldsUtil.isEmpty(answer1)) {
            request.setAttribute("emptyMess2", bundle.getString("con.field.empty"));
            request.getRequestDispatcher("/teacher_add_question").forward(request, response);
        } else if (FieldsUtil.isEmpty(answer2)) {
            request.setAttribute("emptyMess3", bundle.getString("con.field.empty"));
            request.getRequestDispatcher("/teacher_add_question").forward(request, response);
        } else if (FieldsUtil.isEmpty(answer3)) {
            request.setAttribute("emptyMess4", bundle.getString("con.field.empty"));
            request.getRequestDispatcher("/teacher_add_question").forward(request, response);
        } else if (!FieldsUtil.isNumberCorrectAnswer(correctAnswer)) {
            request.setAttribute("answerIncorrect", bundle.getString("con.answer.invalid"));
            request.getRequestDispatcher("/teacher_add_question").forward(request, response);
        } else {
            Question questionFromWeb = new Question(question, answer1, answer2, answer3, correctAnswer, subjectId, langId);

            try {
                questionService.add(questionFromWeb);
            } catch (DaoException e) {
                e.printStackTrace();
            }


            request.setAttribute("msgaddquestion", bundle.getString("con.msgaddquestion"));

            request.getRequestDispatcher("/action_completed").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SubjectService subjectService = new SubjectService();
        LanguageService languageService = new LanguageService();

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int langId = Integer.parseInt(request.getParameter("langId"));

        String subject = null;
        String lang = null;

        try {
            subject = subjectService.getSubjectById(subjectId);
            lang = languageService.getLanguageById(langId);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("subjectId", subjectId);
        request.setAttribute("langId", langId);
        request.setAttribute("subject", subject);
        request.setAttribute("lang", lang);
        request.getRequestDispatcher("/teacher_add_question").forward(request, response);
    }
}
