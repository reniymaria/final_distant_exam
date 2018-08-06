package com.distant.system.controller.command.factory.impl;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.util.ConfigurationManager;

public class NoFoundCommand implements ActionCommand {

    private static final String ERROR_PAGE_PATH = "path.page.error";

    @Override
    public String execute(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
    }
}