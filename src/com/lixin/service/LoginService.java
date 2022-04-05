package com.lixin.service;

import com.lixin.model.entity.User;

public interface LoginService {
    User login(String username, String password);
}
