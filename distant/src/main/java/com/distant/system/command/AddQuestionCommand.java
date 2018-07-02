package com.distant.system.command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddQuestionCommand extends AbstractCommand {
    @Override
    public void executeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int languageId = Integer.parseInt(request.getParameter("langId"));

        request.setAttribute("subjectId", subjectId);
        request.setAttribute("languageId",languageId);
        request.getRequestDispatcher("/teacher/addquestion.jsp").forward(request, response);

    }

    @Override
    public void executePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
