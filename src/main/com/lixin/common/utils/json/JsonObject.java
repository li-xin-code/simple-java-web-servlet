package com.lixin.common.utils.json;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lixin
 */
public class JsonObject {

    private final Map<String, String> map;

    public JsonObject() {
        map = new LinkedHashMap<>(3);
    }

    public void add(String name, Object value) {
        map.put(name, JsonUtils.toJsonString(value));
    }

    public String getValue(String key) {
        return map.get(key);
    }

    public Set<String> getKeys() {
        return map.keySet();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.append("    \"").append(key).append("\"");
            builder.append(" : ").append(map.get(key));
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        builder.append(" }");
        return builder.toString();
    }

}
