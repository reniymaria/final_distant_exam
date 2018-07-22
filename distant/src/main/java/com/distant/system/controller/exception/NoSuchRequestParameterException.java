package com.distant.system.controller.exception;

/**
 * Thrown when SessionRequestContent param is not defined for chosen type.
 */

public class NoSuchRequestParameterException extends Exception {

    public NoSuchRequestParameterException() {
        super();
    }

    public NoSuchRequestParameterException(String message) {
        super(message);
    }

    public NoSuchRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRequestParameterException(Throwable cause) {
        super(cause);
    }
}
