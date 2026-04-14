package com.spectacles.spectacles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spectacles.spectacles.model.User;
import com.spectacles.spectacles.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // 🔐 LOGIN METHOD
    public User login(String username, String password) {

        System.out.println("INPUT: " + username + " " + password);

        User user = repo.findByUsername(username);

        if (user != null) {
            System.out.println("DB: " + user.getUsername() + " " + user.getPassword());
        } else {
            System.out.println("USER NOT FOUND");
        }

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    // 📝 REGISTER METHOD
    public User register(User user) {

    // check if username already exists
        User existing = repo.findByUsername(user.getUsername());

        if (existing != null) {
            return null; // username already taken
        }

        return repo.save(user);
    }
}