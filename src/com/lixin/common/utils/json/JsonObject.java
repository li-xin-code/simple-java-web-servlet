package com.lixin.common.utils.json;

import com.lixin.common.utils.StrUtils;

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

    public void add(String name, String value) {
        map.put(name, value);
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
            String value = map.get(key);
            if (StrUtils.isNum(value)) {
                builder.append(" : ").append(value);
            } else {
                builder.append(" : \"").append(value).append("\"");
            }
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        builder.append(" }");
        return builder.toString();
    }

}
