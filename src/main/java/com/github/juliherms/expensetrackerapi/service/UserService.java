package com.github.juliherms.expensetrackerapi.service;

import com.github.juliherms.expensetrackerapi.entity.User;
import com.github.juliherms.expensetrackerapi.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);

    User read(Long id);

    User update(UserModel user, Long id);

    void delete(Long id);
}
