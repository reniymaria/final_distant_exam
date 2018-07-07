package com.distant.system.command.factory.impl.student;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.MarkService;
import com.distant.system.util.ConfigurationManager;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class StudentExamListCommand implements ActionCommand {

    private static final String LANGUAGE = "language";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String MARK_RESULTS_PATH = "path.page.student.marks";

    private MarkService markService = new MarkService();


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageList;

        Locale locale = null;
        int studentId = 0;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
            studentId = (int) requestContent.getSessionAttribute("studentID");
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);


        try {
            if (markService.allStudentMarks(studentId) == 0) {
                requestContent.setAttribute("listEmpty", bundle.getString("con.list.empty"));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }


        int page = 1;
        int recordsPerPage = 3;
        try {
            if (requestContent.getParameter("page") != null) {
                page = Integer.parseInt(requestContent.getParameter("page"));
            }
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }

        List<ExamResult> list = null;
        try {
            list = markService.numberOfStudentMarks(studentId, (page - 1), recordsPerPage);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = markService.allStudentMarks(studentId);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        requestContent.setAttribute("ExamList", list);
        requestContent.setAttribute("noOfPages", noOfPages);
        requestContent.setAttribute("currentPage", page);

        pageList = ConfigurationManager.getProperty(MARK_RESULTS_PATH);

        return pageList;
    }
}
