package com.github.juliherms.expensetrackerapi.controller;

import com.github.juliherms.expensetrackerapi.entity.User;
import com.github.juliherms.expensetrackerapi.entity.UserModel;
import com.github.juliherms.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.github.juliherms.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("users/profile")
    public ResponseEntity<User> get(){
        return new ResponseEntity<User>(userService.read(), HttpStatus.OK);
    }

    @PutMapping("users/profile")
    public ResponseEntity<User> update(@RequestBody UserModel user) {
        User updatedUser = userService.update(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("users/deactivate")
    public ResponseEntity<HttpStatus> delete() throws ResourceNotFoundException {
        userService.delete();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
