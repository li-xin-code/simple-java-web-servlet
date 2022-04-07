package com.lixin.service;

import com.lixin.model.entity.User;

public interface LoginService {
    /**
     * login
     *
     * @param username username
     * @param password password
     * @return user
     */
    User login(String username, String password);
}
