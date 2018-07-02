package com.distant.system.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand implements Serializable {
    
    private static final long serialVersionUID = 8879403039606311780L;

    public abstract void executeGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    public abstract void executePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;
}
