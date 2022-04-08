package com.lixin.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lixin
 */
public class JsonUtils {

    private JsonUtils() {
    }

    public static JsonObject getJsonObj() {
        return new JsonObject();
    }


    public static class JsonObject {

        private final Map<String, String> map;

        private JsonObject() {
            map = new HashMap<>(16);
        }

        public void add(String name, Object value) {
            map.put(name, value.toString());
        }

        public String toJsonString() {
            StringBuilder builder = new StringBuilder("{");
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                builder.append("\"").append(key).append("\"");
                builder.append(":\"").append(map.get(key)).append("\"");
                if (iterator.hasNext()) {
                    builder.append(",");
                }
            }
            builder.append("}");
            return builder.toString();
        }

    }
}

