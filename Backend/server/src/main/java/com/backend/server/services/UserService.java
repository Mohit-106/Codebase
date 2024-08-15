package com.backend.server.services;
import com.backend.server.entities.User;
import java.util.Optional;
import java.util.List;


public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String ID);
    Optional<User> updateUser(User user);

    User getUserByPhoneNumber(String phoneNumber);
    User getUserByEmail(String phoneNumber);
    
    void deleteUser(String ID);
    boolean isUserExist(String userID);
    List<User> getAllUsers();
}
