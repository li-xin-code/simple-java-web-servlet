package com.lixin.servlet;

import com.lixin.common.utils.json.JsonObject;
import com.lixin.common.utils.json.JsonUtils;

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
        JsonObject result = JsonUtils.httpResult
                (status, "request error", "error handler");
        resp.setContentType("application/json");
        writer.print(result);
    }

}
