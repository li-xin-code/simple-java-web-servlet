package com.lixin.service;

import com.lixin.model.entity.User;

/**
 * 登陆服务
 *
 * @author lixin
 */
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
