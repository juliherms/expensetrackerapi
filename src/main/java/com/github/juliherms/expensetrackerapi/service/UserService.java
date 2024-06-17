package com.github.juliherms.expensetrackerapi.service;

import com.github.juliherms.expensetrackerapi.entity.User;
import com.github.juliherms.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);

    User read();

    User update(UserModel user);

    void delete();

    User getLoggedInUser();
}
