package com.lixin.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lx
 */
@WebServlet(name = "simpleServlet", urlPatterns = "/message")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = -474218327523067831L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.getWriter().print("A simple message.");
    }
}
