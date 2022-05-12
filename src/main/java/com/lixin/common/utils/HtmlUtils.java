package com.lixin.common.utils;

/**
 * @author lx
 * @date 2022/4/7
 */
public class HtmlUtils {

    private HtmlUtils() {
    }

    private final static String BASE_TEMPLATE =
            "<!DOCTYPE html> " +
                    "<html lang=\"en\"> " +
                    "<head> <meta charset=\"UTF-8\"> " +
                    "<title>%s</title></head><body>%s</body" +
                    "></html>";

    public static String getBaseHtml(String title, String body) {
        return String.format(BASE_TEMPLATE, title, body);
    }
}
