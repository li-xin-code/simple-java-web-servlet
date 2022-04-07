package com.lixin.dao;

import com.lixin.model.entity.User;

public interface UserDao {

    /**
     * find user by username
     *
     * @param username username
     * @return user
     */
    User findOne(String username);

    /**
     * add user
     *
     * @param user user
     * @return succeed or not
     */
    Boolean addUser(User user);

}
