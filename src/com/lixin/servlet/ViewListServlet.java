package com.lixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * @author lx
 * @date 2022/4/5
 */
@WebServlet(name = "viewListServlet", urlPatterns = "/view-list")
public class ViewListServlet extends ViewServlet {

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Set<?> viewList = getViewList();
        PrintWriter writer = resp.getWriter();
        viewList.forEach(s -> writer.print(s + ";"));
    }
}