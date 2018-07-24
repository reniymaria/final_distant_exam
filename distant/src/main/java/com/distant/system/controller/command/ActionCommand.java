package com.distant.system.controller.command;

import com.distant.system.controller.SessionRequestContent;

/**
 * Defines the command method for processing the request and forming the
 * response.
 */
public interface ActionCommand {
    /**
     * Processes the request and forms the response to necessary page.
     *
     * @param requestContent
     *            an {@link SessionRequestContent} object that contains request atributes,
     *            parameters, session attributes
     *
     */
    String execute(SessionRequestContent requestContent);
}