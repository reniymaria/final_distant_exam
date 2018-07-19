package com.distant.system.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    private HashUtil() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }



}
