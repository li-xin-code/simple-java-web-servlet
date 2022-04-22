package com.lixin.servlet;

import com.lixin.common.utils.HtmlUtils;
import com.lixin.model.entity.User;
import com.lixin.model.form.LoginForm;
import com.lixin.service.UserService;
import com.lixin.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lixin
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -5167442880274699389L;
    private final UserService userService;

    public LoginServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null) {
            return;
        }
        User loginUser = userService.login(new LoginForm(username, password));
        if (loginUser != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", loginUser.getUsername());
            resp.sendRedirect(req.getContextPath() + "/user-index");
        } else {
            PrintWriter writer = resp.getWriter();
            String content = "用户名或密码错误，登陆失败";
            writer.print(HtmlUtils.getBaseHtml("login", content));
        }
    }

}
