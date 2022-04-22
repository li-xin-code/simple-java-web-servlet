package com.lixin.dao;

import com.lixin.model.entity.User;

import java.util.List;

/**
 * @author lixin
 */
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

    /**
     * all users
     *
     * @return list
     */
    List<User> list();

    /**
     * Does the username exist.
     *
     * @param username ...
     * @return ...
     */
    Boolean isExist(String username);

}
