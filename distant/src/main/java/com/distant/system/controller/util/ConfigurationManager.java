package com.distant.system.controller.util;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.config");

    private ConfigurationManager() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}