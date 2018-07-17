package com.distant.system.service.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 782056315821807740L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
