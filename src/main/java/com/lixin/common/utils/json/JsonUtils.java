package com.lixin.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.lixin.common.enums.HttpStatus;
import com.lixin.common.utils.StrUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lixin
 */
public class JsonUtils {

    private static final Set<Class<?>> BASE = new HashSet<>();

    static {
        Class<?>[] baseArr = {Integer.class, Short.class, Long.class,
                Byte.class, Boolean.class, Float.class, Double.class};
        BASE.addAll(Arrays.asList(baseArr));
    }


    private JsonUtils() {
    }

    public static JsonObject httpResult(
            int status, String message, Object data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("status", status);
        jsonObject.add("message", message);
        jsonObject.add("data", data);
        return jsonObject;
    }

    public static JsonObject httpSuccess(Object data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("status", HttpStatus.SUCCESS.getCode());
        jsonObject.add("message", "success");
        jsonObject.add("data", data);
        return jsonObject;
    }

    public static JsonObject httpFail(String msg) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("status", HttpStatus.FAIL.getCode());
        jsonObject.add("message", msg);
        return jsonObject;
    }

    public static String toJsonString(Object o) {
        return JSON.toJSONString(o);
    }

    public static JsonObject parse(String json) {
        JsonObject jsonObject = new JsonObject();
        json = json.replaceAll("[{} ]", "");
        String[] elements = json.split(",");
        for (String element : elements) {
            String[] strings = element.split(":");
            if (strings.length > 2) {
                System.out.println(Arrays.toString(strings));
                throw new RuntimeException("json parse fail");
            }
            String key = strings[0];
            key = key.replaceAll("\"", "");
            String value = strings[1];
            if (!StrUtils.isNum(value)) {
                value = value.substring(1, value.length() - 1);
            }
            jsonObject.add(key, value);
        }
        return jsonObject;
    }

}

