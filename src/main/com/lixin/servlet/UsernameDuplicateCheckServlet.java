package com.lixin.servlet;

import com.lixin.common.enums.HttpStatus;
import com.lixin.common.utils.json.JsonUtils;
import com.lixin.service.UserService;
import com.lixin.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户名查重
 *
 * @author lixin
 */
@WebServlet(name = "usernameDuplicateCheckServlet", urlPatterns = "/username/*")
public class UsernameDuplicateCheckServlet extends HttpServlet {

    private static final long serialVersionUID = -4138180122129763186L;

    private final UserService userService;

    public UsernameDuplicateCheckServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        String path = "/username/";
        String username = uri.substring(uri.indexOf(path) + path.length());
        PrintWriter writer = resp.getWriter();
        String check = "/";
        resp.setContentType("application/json");
        if (username.contains(check)) {
            resp.setStatus(HttpStatus.FAIL.getCode());
            writer.println(JsonUtils.httpFail("Bad Request"));
            return;
        }
        if (userService.checkUsername(username)) {
            resp.setStatus(HttpStatus.FAIL.getCode());
            writer.println(JsonUtils.httpFail("username already exists."));
        } else {
            writer.println(JsonUtils.httpSuccess("username is available."));
        }
    }
    
}
