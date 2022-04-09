package com.lixin.filter;

import com.lixin.common.utils.json.JsonObject;
import com.lixin.common.utils.json.JsonUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author lixin
 */
@WebFilter(filterName = "jsonFilter", urlPatterns = "/*")
public class JsonFilter extends GenericFilter {

    private static final String JSON_CONTENT_TYPE = "application/json";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String contentType = req.getContentType();
        if (JSON_CONTENT_TYPE.equals(contentType)) {
            BufferedReader reader = req.getReader();
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JsonObject jsonObject = JsonUtils.parse(builder.toString());
            jsonObject.getKeys().forEach(k -> req.setAttribute(k, jsonObject.getValue(k)));
        }
        chain.doFilter(req, response);
    }

}
