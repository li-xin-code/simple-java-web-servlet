package com.lixin.servlet;

import com.lixin.model.entity.User;
import com.lixin.service.LoginService;
import com.lixin.service.impl.LoginServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lixin
 */
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -5167442880274699389L;
    private final LoginService loginService;

    public LoginServlet() {
        loginService = new LoginServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = loginService.login(username, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", loginUser);
    }

}
