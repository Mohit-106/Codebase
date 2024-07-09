package com.backend.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.backend.server.forms.UserForm;
import com.backend.server.services.UserService;
import com.backend.server.entities.User;

import org.springframework.ui.Model;

@Controller
public class pageController {
     @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(){
        System.out.println("Home page loading....");
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        System.out.println("About page loading....");
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        System.out.println("Services page loading....");
        return "services";
    }

    @RequestMapping("/contacts")
    public String contacts(){
        System.out.println("contacts page loading....");
        return "contacts";
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("Services page loading....");
        return "login";
    }
    
    @RequestMapping("/register")
    public String register(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

     @RequestMapping(value = "/do-register", method=RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm){      

        // Creating user from user form 

        User user = new User();
        user.setName(userForm.getName());
        user.setGovtID(userForm.getGovtID());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        User saveUser = userService.saveUser(user);
      
        return "redirect:/register";
    }



}
