package com.lixin.service;

import com.lixin.model.entity.User;

import java.util.List;

/**
 * @author lixin
 */
public interface UserService {

    /**
     * add user
     *
     * @param user user
     * @return success or fail
     */
    Boolean add(User user);

    /**
     * all user
     *
     * @return list
     */
    List<User> list();

}
