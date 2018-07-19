package com.distant.system.controller.command.factory.impl;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.ConfigurationManager;

public class LogOutCommand implements ActionCommand {
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String USER = "user";


    @Override
    public String executePost(SessionRequestContent requestContent) {
        requestContent.removeSessionAttribute(USER);
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }
}
