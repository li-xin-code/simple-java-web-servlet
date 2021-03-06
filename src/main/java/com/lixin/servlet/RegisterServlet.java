package com.lixin.servlet;

import com.lixin.common.utils.json.JsonObject;
import com.lixin.common.utils.json.JsonUtils;
import com.lixin.model.entity.User;
import com.lixin.model.form.RegisterForm;
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
        String username = (String) req.getAttribute("username");
        String password = (String) req.getAttribute("password");
        String repeatPassword = (String) req.getAttribute("repeat_password");

        User user = userService.register(new RegisterForm(username, password, repeatPassword));
        HttpSession session = req.getSession();
        session.setAttribute("user", user.getUsername());

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject success = JsonUtils.httpSuccess(user.getUsername());
        writer.print(success);
    }

}
