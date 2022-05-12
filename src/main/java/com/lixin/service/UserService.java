package com.lixin.service;

import com.lixin.model.entity.User;
import com.lixin.model.form.LoginForm;
import com.lixin.model.form.RegisterForm;

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

    /**
     * login
     *
     * @param form form
     * @return login user
     */
    User login(LoginForm form);

    /**
     * user
     *
     * @param form form
     * @return user user
     */
    User register(RegisterForm form);

    /**
     * check if username is available
     *
     * @param username username
     * @return true or false
     */
    Boolean checkUsername(String username);

}
