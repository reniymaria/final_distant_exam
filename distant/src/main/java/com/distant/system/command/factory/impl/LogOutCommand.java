package com.distant.system.command.factory.impl;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.util.ConfigurationManager;

public class LogOutCommand implements ActionCommand {
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String ROLE_ATTR = "role";


    @Override
    public String executePost(SessionRequestContent requestContent) {
        requestContent.removeSessionAttribute(ROLE_ATTR);
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }
}
