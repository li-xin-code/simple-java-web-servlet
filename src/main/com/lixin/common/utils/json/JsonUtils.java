package com.lixin.common.utils.json;

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

    public static String toJsonString(Object o) {
        if (o == null) {
            return "null";
        }
        Class<?> aClass = o.getClass();
        System.out.println(aClass);
        if (BASE.contains(aClass)) {
            return o.toString();
        } else if (String.class == aClass) {
            return String.format("\"%s\"", o);
        } else if (Character.class == aClass) {
            return String.format("'%s'", o);
        } else if (aClass.isArray()) {
            if (aClass == byte[].class) {
                return Arrays.toString((byte[]) o);
            } else if (aClass == short[].class) {
                return Arrays.toString((short[]) o);
            } else if (aClass == int[].class) {
                return Arrays.toString((int[]) o);
            } else if (aClass == long[].class) {
                return Arrays.toString((long[]) o);
            } else if (aClass == char[].class) {
                return Arrays.toString((char[]) o);
            } else if (aClass == float[].class) {
                return Arrays.toString((float[]) o);
            } else if (aClass == double[].class) {
                return Arrays.toString((double[]) o);
            } else if (aClass == boolean[].class) {
                return Arrays.toString((boolean[]) o);
            } else {
                return Arrays.deepToString((Object[]) o);
            }
        }
        return o.toString();
    }

    public static JsonObject parse(String json) {
        JsonObject jsonObject = new JsonObject();
        json = json.replaceAll("[{} ]", "");
        String[] elements = json.split(",");
        for (String element : elements) {
            String[] strings = element.split(":");
            if (strings.length > 2) {
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

