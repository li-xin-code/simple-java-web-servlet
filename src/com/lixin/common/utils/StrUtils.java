package com.lixin.common.utils;

import java.util.regex.Pattern;

/**
 * @author lixin
 */
public class StrUtils {

    private StrUtils() {
    }

    public static boolean isNum(String str) {
        return Pattern.matches("[0-9]*", str);
    }

}
