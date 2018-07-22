package com.distant.system.controller;

import javax.servlet.*;
import java.io.IOException;

/**
 * The encoding filter. Sets encoding to request and response
 */

public class EncodingFilter implements Filter {

    /** The encoding by default */
    private String encoding = "utf-8";

    /**
     * Sets character encoding to the request and response.
     *
     * @see {@link Filter#doFilter}
     */
    public void doFilter(ServletRequest request,ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }


    /**
     * Gets the value of context-wide initialization parameter keeps the name of
     * character encoding or takes the default name if it's not defined in context
     * parameters.
     *
     * @see {@link Filter#init}
     */
    public void init(FilterConfig filterConfig){
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    @Override
    public void destroy() {

    }
}