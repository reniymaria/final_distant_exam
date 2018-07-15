package com.distant.system.controller;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.command.factory.ActionFactory;
import com.distant.system.controller.util.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    private static final String PATH_PAGE_ERROR = "path.page.error";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        SessionRequestContent requestContent = new SessionRequestContent(request);
        if ("POST".equals(request.getMethod())) {
            page = command.executePost(requestContent);

        } else if ("GET".equals(request.getMethod())) {
            page = command.executeGet(requestContent);
        }

        if (page != null) {
            requestContent.insertValues(request);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(ConfigurationManager.getProperty(PATH_PAGE_ERROR));
        }
    }

}
