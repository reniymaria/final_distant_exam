package com.distant.system.controller.util;

import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommandUtil {

    private static final String LANGUAGE = "language";
    private static final String RU_DEFAULT = "ru";
    private static final String BASE_NAME = "i18n.content";


    private static final Logger LOGGER = LogManager.getLogger(CommandUtil.class);

    private CommandUtil() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static ResourceBundle takeBundle(SessionRequestContent requestContent) {
        ResourceBundle bundle;
        Locale locale;
        try {
            locale = new Locale(requestContent.getSessionAttribute(LANGUAGE).toString());
        } catch (NoSuchRequestParameterException e) {
            LOGGER.warn("Parameter locale is not found. Set locale ru", e);
            locale = new Locale(RU_DEFAULT);
        }
        bundle = ResourceBundle.getBundle(BASE_NAME, locale);

        return bundle;
    }
}
