// 

package com.backend.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.backend.server.forms.UserForm;
import com.backend.server.helper.Message;
import com.backend.server.helper.MessageType;
import com.backend.server.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.backend.server.entities.User;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

@Controller
public class pageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home() {
        System.out.println("Home page loading....");
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        System.out.println("About page loading....");
        return "about";
    }

    @RequestMapping("/services")
    public String services() {
        System.out.println("Services page loading....");
        return "services";
    }

    @RequestMapping("/contacts")
    public String contacts() {
        System.out.println("contacts page loading....");
        return "contacts";
    }

    @RequestMapping("/login")
    public String login() {
        System.out.println("Services page loading....");
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        if (rBindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAge(userForm.getAge());
        user.setGender(userForm.getGender());
        user.setEphoneNumber(userForm.getEphoneNumber());
        
        User saveUser = userService.saveUser(user);

        Message message = Message.builder().content("You have registered successfully").type(MessageType.green).build();
        session.setAttribute("message", message);

        return "redirect:/register";
    }

    // REST API for Android
    @PostMapping("/user-register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserForm userForm, BindingResult rBindingResult) {
        if (rBindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors occurred");
        }
        User user = new User();
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAge(userForm.getAge());
        user.setGender(userForm.getGender());
        user.setEphoneNumber(userForm.getEphoneNumber());
        System.out.println(userForm.getName());
        System.out.println(userForm.getPhoneNumber());
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
