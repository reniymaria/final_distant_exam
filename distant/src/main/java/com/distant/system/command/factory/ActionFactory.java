package com.distant.system.command.factory;

import com.distant.system.command.ActionCommand;
import com.distant.system.command.CommandEnum;
import com.distant.system.command.factory.impl.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String COMMAND_PARAM = "command";
    private static ActionCommand lastCommand;

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand current = new EmptyCommand();
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