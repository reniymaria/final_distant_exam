package com.distant.system.command.factory.impl.teacher;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Subject;
import com.distant.system.exception.NoSuchRequestParameterException;
import com.distant.system.service.SubjectService;
import com.distant.system.util.ConfigurationManager;

import java.util.List;

public class TeacherSubjectsCommand implements ActionCommand {

    private static final String TEACHER_LIST_SUBJECT_PATH = "path.page.teacher.list.subject";
    private SubjectService subjectService = new SubjectService();


    @Override
    public String executePost(SessionRequestContent requestContent) {
        return null;
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {

        String pageString;

        int page = 1;
        int recordsPerPage = 3;
        try {
            if (requestContent.getParameter("page") != null) {
                page = Integer.parseInt(requestContent.getParameter("page"));
            }
        } catch (NoSuchRequestParameterException e) {
            e.printStackTrace();
        }

        List<Subject> list = null;
        try {
            list = subjectService.numberOfAllSubjects((page - 1), recordsPerPage);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfRecords = 0;
        try {
            noOfRecords = subjectService.getSizeAllSubjects();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        requestContent.setAttribute("SubjectList", list);
        requestContent.setAttribute("noOfPages", noOfPages);
        requestContent.setAttribute("currentPage", page);

        pageString = ConfigurationManager.getProperty(TEACHER_LIST_SUBJECT_PATH);

        return pageString;
    }
}
