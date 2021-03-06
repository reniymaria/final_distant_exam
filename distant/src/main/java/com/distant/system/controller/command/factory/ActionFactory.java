package com.distant.system.controller.command.factory;

import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.command.CommandEnum;
import com.distant.system.controller.command.factory.impl.NoFoundCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * A factory for defining commands and saving the last command
 */
public class ActionFactory {

    private static final String COMMAND_PARAM = "command";
    private static ActionCommand lastCommand;

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand current = new NoFoundCommand();
        String userCommand = request.getParameter(COMMAND_PARAM);

        if (userCommand == null || userCommand.isEmpty()) {
            if (lastCommand == null) {
                return current;
            } else {
                return lastCommand;
            }
        }

        CommandEnum currentEnum = CommandEnum.valueOf(userCommand.toUpperCase());
        current = currentEnum.getCurrentCommand();

        lastCommand = current;

        return current;
    }
}