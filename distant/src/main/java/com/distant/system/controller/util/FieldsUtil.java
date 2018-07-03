package com.distant.system.controller.util;


import java.util.List;

/**
 * Defines static methods for fields validation.
 */
public class FieldsUtil {


    /**
     * Not instantiate utility class.
     */
    private FieldsUtil() {
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

}
