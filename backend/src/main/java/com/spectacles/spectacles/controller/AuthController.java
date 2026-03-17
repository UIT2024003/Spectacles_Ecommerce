package com.spectacles.spectacles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spectacles.spectacles.model.User;
import com.spectacles.spectacles.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    // 🔐 LOGIN
    @PostMapping("/login")
    public User login(@RequestBody User user) {

        System.out.println("LOGIN API HIT 🔥");
        System.out.println("REQUEST DATA: " + user.getUsername() + " " + user.getPassword());

        User loggedUser = service.login(user.getUsername(), user.getPassword());

        if (loggedUser != null) {
            System.out.println("LOGIN SUCCESS ✅");
        } else {
            System.out.println("LOGIN FAILED ❌");
        }

        return loggedUser;
    }

    // 📝 REGISTER (optional)
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }
}