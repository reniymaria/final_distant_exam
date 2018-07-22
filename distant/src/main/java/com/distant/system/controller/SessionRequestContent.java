package com.distant.system.controller;


import com.distant.system.controller.exception.NoSuchRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * SessionRequestContent work with request attributes, parameters andsession.
 */

public class SessionRequestContent {
    /**
     * HashMap of request attributes
     * HashMap of request parameters
     * HashMap of session attributes
     */
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }


    public SessionRequestContent(HttpServletRequest request) {
        this.request = request;
        extractValues(request);
    }
    /**
     * Remove of session attribute
     *  @param attribute String name
     */
    public void removeSessionAttribute(String attribute) {
        sessionAttributes.remove(attribute);
        request.getSession().removeAttribute(attribute);
    }

    /**
     * Find parameter by name
     *  @param parameterName of the parameter
     * @return the String name of parameter
     */

    public String getParameter(String parameterName) throws NoSuchRequestParameterException {
        if (requestParameters.get(parameterName) != null) {
            return requestParameters.get(parameterName)[0];
        } else {
            throw new NoSuchRequestParameterException(parameterName);
        }
    }

    /**
     * Set request attribute
     *  @param attributeName name of the parameter
     *  @param attributeValue Object of the parameter
     */
    public void setAttribute(String attributeName, Object attributeValue) {
        requestAttributes.put(attributeName, attributeValue);
    }

    /**
     * Set session attribute
     *  @param attributeName name of the parameter
     *  @param attributeValue Object of the parameter
     */
    public void setSessionAttribute(String attributeName, Object attributeValue) {
        sessionAttributes.put(attributeName, attributeValue);
    }
    /**
     * Get session attribute
     *  @param attributeName name of the parameter
     */
    public Object getSessionAttribute(String attributeName) throws NoSuchRequestParameterException {
        if (sessionAttributes.get(attributeName) != null) {
            return sessionAttributes.get(attributeName);
        } else {
            throw new NoSuchRequestParameterException(attributeName);
        }
    }

    /**
     * Insert Values from map
     *  @param request HttpServletRequest
     */

    public void insertValues(HttpServletRequest request) {
        for (Map.Entry<String, Object> requestAttribute : requestAttributes.entrySet()) {
            request.setAttribute(requestAttribute.getKey(), requestAttribute.getValue());
        }
        for (Map.Entry<String, Object> sessionAttribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(sessionAttribute.getKey(), sessionAttribute.getValue());
        }
    }
    /**
     * Extract Values from map
     *  @param request HttpServletRequest
     */

    private void extractValues(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        extractRequestAttributes(request);
        extractSessionAttributes(request);
    }

    /**
     * Extract request attributes from map
     *  @param request HttpServletRequest
     */
    private void extractRequestAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            Object value = request.getAttribute(name);
            requestAttributes.put(name, value);
        }
    }
    /**
     * Extract session attributes from map
     *  @param request HttpServletRequest
     */
    private void extractSessionAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            Object value = request.getSession().getAttribute(name);
            sessionAttributes.put(name, value);
        }
    }

}
