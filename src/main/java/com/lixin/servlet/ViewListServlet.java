package com.lixin.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author lx
 * @date 2022/4/5
 */
@WebServlet(name = "viewListServlet", urlPatterns = "/view/list")
public class ViewListServlet extends ViewServlet {

    private static final long serialVersionUID = -851211348418813695L;

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Object[] viewArray = getViewList().toArray();
        PrintWriter writer = resp.getWriter();
        writer.print(Arrays.toString(viewArray));
    }

}
