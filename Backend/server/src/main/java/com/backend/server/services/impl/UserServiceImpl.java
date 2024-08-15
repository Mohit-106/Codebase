package com.backend.server.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.server.entities.User;
import com.backend.server.helper.AppConstants;
import com.backend.server.helper.ResourceNotFoundException;
import com.backend.server.repositories.UserRepo;
import com.backend.server.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Override
    public User saveUser(User user) {

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // Password Encoding
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        
        logger.info(user.getProvider().toString());
        return userRepo.save(user);

    }

    @Override
    public Optional<User> getUserById(String ID) {
        return userRepo.findById(ID);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("Usetr not found"));
        // Update 
        user2.setName(user.getName());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setEnabled(user.isEnabled());
        user2.setPhoneNumberVerified(user.isPhoneNumberVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        user2.setAge(user.getAge());
        user2.setGender(user.getGender());
        user2.setEphoneNumber(user.getEphoneNumber());

        // Save user in data base
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String ID) {
        User user2 = userRepo.findById(ID).orElseThrow(()->new ResourceNotFoundException("Usetr not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userID) {
        User user2 = userRepo.findById(userID).orElse(null);
        return user2!=null ? true:false;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
  
    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber).orElseThrow(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(null);
    }
}
