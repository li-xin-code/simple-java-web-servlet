package com.lixin.service.impl;

import com.lixin.dao.UserDao;
import com.lixin.dao.impl.UserDaoImplInMemory;
import com.lixin.model.entity.User;
import com.lixin.model.form.LoginForm;
import com.lixin.model.form.RegisterForm;
import com.lixin.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author lixin
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = UserDaoImplInMemory.getUserDao();
    }

    @Override
    public Boolean add(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User login(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        User user = userDao.findOne(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("username or password wrong");
        }
    }

    @Override
    public User register(RegisterForm form) {
        String username = form.getUsername();
        String password = form.getPassword();
        if (!checkNameFormat(username)) {
            throw new RuntimeException("username format wrong.");
        }
        if (!checkPwdFormat(password)) {
            throw new RuntimeException("password format wrong.");
        }
        if (!password.equals(form.getRepeatPassword())) {
            throw new RuntimeException("password and the repeat password do not match.");
        }
        if (userDao.findOne(username) != null) {
            throw new RuntimeException("username already exists.");
        }
        User user = new User(username, password);
        if (!userDao.addUser(user)) {
            throw new RuntimeException("user fail");
        }
        return user;
    }

    @Override
    public Boolean checkUsername(String username) {
        return userDao.isExist(username);
    }

    private boolean checkNameFormat(String username) {
        String regex = "^\\w{4,12}$";
        return Pattern.matches(regex, username);
    }

    private boolean checkPwdFormat(String pwd) {
        String regex = "^[\\w~`!@#$%^&*\\-+=]{8,16}$";
        return Pattern.matches(regex, pwd);
    }

}
