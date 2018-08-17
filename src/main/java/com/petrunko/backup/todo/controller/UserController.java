package com.petrunko.backup.todo.controller;

import com.petrunko.backup.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "users")
    public @ResponseBody
    ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "users/{userId}")
    public @ResponseBody
    ResponseEntity getUserById(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }
}
