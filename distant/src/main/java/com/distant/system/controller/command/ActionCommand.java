package com.distant.system.controller.command;

import com.distant.system.controller.SessionRequestContent;

public interface ActionCommand {

    String executePost(SessionRequestContent requestContent);

    String executeGet(SessionRequestContent requestContent);
}