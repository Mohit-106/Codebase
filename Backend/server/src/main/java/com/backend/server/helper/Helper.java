package com.backend.server.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.backend.server.entities.User;
import com.backend.server.services.UserService;

@Component
public class Helper {

    @Autowired
    private UserService userService; 

    public User getUser(Authentication authentication){
        if(authentication instanceof OAuth2AuthenticationToken){
            // if user is logged in by Google
            System.out.println("Logged in with Google");
            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = oauth2User.getAttribute("email").toString();
            User user = userService.getUserByEmail(username);
            return user;
        } else {
            // if user is logged in by in-app Signup
            System.out.println("Logged in with Username and Password");
            String username = authentication.getName();
            User user = userService.getUserByPhoneNumber(username);
            return user;
        }
    }
}
