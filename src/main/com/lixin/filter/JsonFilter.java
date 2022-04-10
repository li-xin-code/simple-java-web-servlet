package com.lixin.filter;

import com.lixin.common.enums.HttpStatus;
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
import java.io.PrintWriter;

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
            JsonObject jsonObject;
            try {
                jsonObject = JsonUtils.parse(builder.toString());
                for (String key : jsonObject.getKeys()) {
                    req.setAttribute(key, jsonObject.getValue(key));
                }
            } catch (Exception e) {
                PrintWriter writer = response.getWriter();
                writer.flush();
                JsonObject result = JsonUtils.httpResult(
                        HttpStatus.ERROR.getCode(), "request error", e.getMessage());
                response.setContentType("application/json");
                writer.print(result);
                return;
            }
        }
        chain.doFilter(req, response);
    }

}
