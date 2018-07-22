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
    private static ActionFactory client;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Gets the type of command for processing request and executes this command.
     *
     * @param request
     *            an {@link HttpServletRequest} object that contains the request the
     *            client has made of the servlet
     * @param response
     *            an {@link HttpServletResponse} object that contains the response
     *            the servlet sends to the client
     * @throws ServletException
     *             if ServletException has happened during {@code forward()}
     * @throws IOException
     *             if IOException has happened during {@code SendRedirect()} or
     *             {@code forward()}
     */


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        if (client == null) {
            client = new ActionFactory();
        }

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
