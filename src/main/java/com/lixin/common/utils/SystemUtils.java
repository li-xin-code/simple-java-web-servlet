package com.lixin.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lixin
 */
public class SystemUtils {
    private SystemUtils() {
    }

    public static final String UPLOAD_PATH = "/Users/lixin/Downloads/simple/upload/";
    public static final int MAX_FILE_SIZE = 1024 * 1024 * 10;
    public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
    
    public static final Map<String, String> CONTENT_TYPE_MAP;

    static {
        Map<String, String> contentTypeMap = new HashMap<>();
        contentTypeMap.put("png", "image/png");
        contentTypeMap.put("jpg", "image/jpeg");
        contentTypeMap.put("jpeg", "image/jpeg");
        contentTypeMap.put("git", "image/gif");
        CONTENT_TYPE_MAP = Collections.unmodifiableMap(contentTypeMap);
    }


    private final static String[] CHARS = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 生成一个8位的62进制数
     * Generate an 8-bit Binary 62 number.
     *
     * @return uuid
     */
    public static String uuid() {
        StringBuilder builder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int len = 8;
        for (int i = 0; i < len; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            builder.append(CHARS[x % 0x3E]);
        }
        return builder.toString();
    }

    public static void inputToOutPut(InputStream in, OutputStream out) {
        try {
            int read;
            while ((read = in.read()) != -1) {
                out.write(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
