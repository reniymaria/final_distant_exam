package com.distant.system.service.util;


import com.distant.system.entity.User;
import org.apache.commons.validator.GenericValidator;

import java.util.List;

/**
 * Defines static methods for fields validation.
 */
public class Validation {

    /**
     * Not instantiate utility class.
     */
    private Validation() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static boolean isEmpty(String value) {
        boolean result = false;
        if (value.trim().length() == 0) {
            result = true;
        }
        return result;
    }

    public static boolean isPasswordEqual(String pass1, String pass2) {
        return pass1.equals(pass2);
    }


    public static <T> int sizeOfList(List<T> items) {
        return items.size();
    }

    public static boolean isNumberCorrectAnswer(int number) {
        if (number > 0 && number < 4) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyNum(int value) {
        return value == 0;
    }


    public static boolean isLoginDataValid(String login, String password) {
        if (GenericValidator.isBlankOrNull(login)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(password)) {
            return false;
        }
        return true;
    }

    public static boolean isRegistrationDataValid(User user) {
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();

        if (GenericValidator.isBlankOrNull(login)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(password)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(name)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(surname)) {
            return false;
        }
        return true;
    }

}
