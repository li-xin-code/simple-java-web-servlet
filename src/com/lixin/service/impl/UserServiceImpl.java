package com.lixin.service.impl;

import com.lixin.dao.UserDao;
import com.lixin.dao.impl.UserDaoImplInMemory;
import com.lixin.model.entity.User;
import com.lixin.service.UserService;

import java.util.List;

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

}
