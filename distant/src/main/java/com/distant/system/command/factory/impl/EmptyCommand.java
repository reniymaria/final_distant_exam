package com.distant.system.command.factory.impl;

import com.distant.system.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.util.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

    private static final String ERROR_PAGE_PATH = "path.page.error";

    @Override
    public String executePost(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
    }

    @Override
    public String executeGet(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
    }
}