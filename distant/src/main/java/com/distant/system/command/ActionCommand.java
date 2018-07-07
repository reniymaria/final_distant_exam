package com.distant.system.command;

import com.distant.system.controller.SessionRequestContent;

public interface ActionCommand {

    String executePost(SessionRequestContent requestContent);

    String executeGet(SessionRequestContent requestContent);
}