package com.lixin.servlet;

import com.lixin.model.entity.User;
import com.lixin.service.UserService;
import com.lixin.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lx
 */
@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 6656332812299020673L;
    private final UserService userService;

    public RegisterServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        System.out.println(user);
        userService.add(user);
        HttpSession session = req.getSession();
        session.setAttribute("user", username);
        resp.sendRedirect(req.getContextPath() + "/");
    }

}
