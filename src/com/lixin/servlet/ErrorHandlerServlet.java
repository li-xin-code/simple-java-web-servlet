package com.lixin.servlet;

import com.lixin.common.utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lixin
 */
@WebServlet(name = "errorHandlerServlet", urlPatterns = "/error")
public class ErrorHandlerServlet extends HttpServlet {
    private static final long serialVersionUID = -854417701604871930L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        int status = resp.getStatus();
        PrintWriter writer = resp.getWriter();
        writer.flush();
        JsonUtils.JsonObject result = JsonUtils.getJsonObj();
        result.add("status", status);
        result.add("message", "request error");
        System.out.println("/error => " + result.toJsonString());
        resp.setContentType("application/json");
        writer.print(result.toJsonString());
    }

}
