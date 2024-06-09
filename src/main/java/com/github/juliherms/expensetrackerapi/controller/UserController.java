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

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity<User>(userService.read(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@RequestBody UserModel user, @PathVariable Long id) {
        User updatedUser = userService.update(user,id);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) throws ResourceNotFoundException {
        userService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
