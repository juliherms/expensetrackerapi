package com.github.juliherms.expensetrackerapi.service.impl;

import com.github.juliherms.expensetrackerapi.entity.User;
import com.github.juliherms.expensetrackerapi.entity.UserModel;
import com.github.juliherms.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.github.juliherms.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.github.juliherms.expensetrackerapi.repository.UserRepository;
import com.github.juliherms.expensetrackerapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public final String EXISTS_EMAIL_FROM_USER = "User is already registered with email";
    public final String USER_NOT_FOUND = "User not found for the id";

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistsException(String.format("%s : %s", EXISTS_EMAIL_FROM_USER, user.getEmail()));
        }

        User newUser = new User();
        BeanUtils.copyProperties(user,newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User read(Long id) {
        return  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("%s : %s", USER_NOT_FOUND, id)));
    }

    @Override
    public User update(UserModel user, Long id) {
        User foundUser = read(id); //find user to update

        //TODO: check to it's possible to update email and password
        foundUser.setName(user.getName() != null ? user.getName() : foundUser.getName());
        foundUser.setName(user.getEmail() != null ? user.getEmail() : foundUser.getEmail());
        foundUser.setName(user.getPassword() != null ? user.getPassword() : foundUser.getPassword());
        foundUser.setAge(user.getAge() != null ? user.getAge() : foundUser.getAge());

        return userRepository.save(foundUser);
    }

    @Override
    public void delete(Long id) {
        User foundUser = read(id);
        userRepository.delete(foundUser);
    }
}
