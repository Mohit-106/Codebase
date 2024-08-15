package com.backend.server.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.backend.server.entities.User;
import com.backend.server.helper.Helper;

@ControllerAdvice
public class RootController {

    @Autowired
    private Helper helper;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication){
        if(authentication == null){
            return;
        }
        System.out.println("Adding logged-in User to the model");
        User user = helper.getUser(authentication);
        model.addAttribute("user", user);
    }
}



