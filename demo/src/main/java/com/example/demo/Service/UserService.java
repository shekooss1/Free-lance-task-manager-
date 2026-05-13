package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        // Business rule: usernames must be unique
        if (userRepository.existsByName(user.getName())) {
            throw new RuntimeException("Username '" + user.getName() + "' is already taken.");
        }

        return userRepository.save(user);   
    }
 public User loginUser(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("No user found with email: " + email));

    if (!user.getPassword().equals(password)) {
        throw new RuntimeException("Incorrect password.");
    }
    return user;
}
    
    public List<User> getAllUsers() {
        return userRepository.findAll();   
    }

    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete — user not found with id: " + id);
        }
        userRepository.deleteById(id);  
    }
}