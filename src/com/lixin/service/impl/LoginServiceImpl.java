package com.lixin.service.impl;

import com.lixin.dao.UserDao;
import com.lixin.dao.impl.UserDaoImplInMemory;
import com.lixin.model.entity.User;
import com.lixin.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private final UserDao userDao;

    public LoginServiceImpl() {
        userDao = new UserDaoImplInMemory();
    }

    @Override
    public User login(String username, String password) {
        User result = null;
        User one = userDao.findOne(username);
        if (one != null) {
            if (password.equals(one.getPassword())) {
                result = one;
            }
        }
        return result;
    }
}
