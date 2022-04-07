package com.lixin.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author lixin
 */
@WebServlet(name = "userIndexServlet", urlPatterns = "/user-index")
public class UserIndexServlet extends HttpServlet {

    private static final long serialVersionUID = 7037437391071557329L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;");
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        PrintWriter writer = resp.getWriter();
        if (user == null) {
            writer.println("您还没有登陆，请<a href = '/simple/view/login'>登陆</a>");
        } else {
            writer.println("欢迎您：" + user + ",<a href = '/simple/logout'>退出登陆</a>");
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge((int) TimeUnit.MINUTES.toSeconds(30));
            cookie.setPath("/simple");
            resp.addCookie(cookie);
        }
    }

}
