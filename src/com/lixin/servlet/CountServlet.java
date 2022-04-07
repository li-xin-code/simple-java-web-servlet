package com.lixin.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lx
 */
@WebServlet(name = "countServlet", urlPatterns = "/count")
public class CountServlet extends HttpServlet {
    private static final long serialVersionUID = -8907438645539918587L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ServletContext servletContext = getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        count = count == null ? 1 : count + 1;
        servletContext.setAttribute("count", count);
        resp.getWriter().println(count);
    }
}
