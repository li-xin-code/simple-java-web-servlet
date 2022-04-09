package com.lixin.common.utils.json;

import com.lixin.common.utils.StrUtils;

/**
 * @author lixin
 */
public class JsonUtils {

    private JsonUtils() {
    }

    public static JsonObject httpResult(
            int status, String message, Object data) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("status", String.valueOf(status));
        jsonObject.add("message", message);
        jsonObject.add("data", String.valueOf(data));
        return jsonObject;
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

