package com.distant.system.dao.exception;

import com.distant.system.dao.DAOFactory;

/**
 * Thrown when {@link DAOFactory} is not defined for chosen type.
 */
public class UnsupportedStoradgeTypeException extends RuntimeException {

    private static final long serialVersionUID = -3862687682355132231L;

    public UnsupportedStoradgeTypeException(String message) {
        super(message);
    }

}