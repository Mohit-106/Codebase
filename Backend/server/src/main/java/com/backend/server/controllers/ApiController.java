package com.backend.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.server.entities.User;
import com.backend.server.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UserService userService;

    @GetMapping("/patient/{id}")
    public User getPatientDetails(@PathVariable String id) {
        return userService.getUserByPhoneNumber(id);
    }
    
}
