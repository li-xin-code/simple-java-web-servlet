package com.lixin.common.filter;

import com.lixin.common.enums.HttpStatus;
import com.lixin.common.utils.json.JsonObject;
import com.lixin.common.utils.json.JsonUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lixin
 */
@WebFilter(filterName = "exceptionFilter", urlPatterns = "/*")
public class ExceptionFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            ((HttpServletResponse) response).setStatus(HttpStatus.ERROR.getCode());
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.flush();
            JsonObject result = JsonUtils.httpResult(
                    HttpStatus.ERROR.getCode(), "request exception", e.getMessage());
            writer.print(result);
        }
    }
}
