package com.lixin.dao.impl;

import com.lixin.dao.UserDao;
import com.lixin.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImplInMemory implements UserDao {

    private final List<User> userList = new ArrayList<>(10);

    public UserDaoImplInMemory() {
        userList.add(new User("admin", "admin"));
        userList.add(new User("user", "password"));
        userList.add(new User("张三", "123456"));
    }

    @Override
    public User findOne(final String username) {
        User result = null;
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public Boolean addUser(User user) {
        userList.add(user);
        return true;
    }
}
