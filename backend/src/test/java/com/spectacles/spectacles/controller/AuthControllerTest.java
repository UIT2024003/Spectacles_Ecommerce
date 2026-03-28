package com.spectacles.spectacles.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectacles.spectacles.model.User;
import com.spectacles.spectacles.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ LOGIN SUCCESS
    @Test
    void testLoginSuccess() throws Exception {

        System.out.println("Running testLoginSuccess");

        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin123");
        user.setRole("ADMIN");

        when(service.login("admin", "admin123")).thenReturn(user);

        mockMvc.perform(post("/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"));
    }

    // ❌ LOGIN FAIL
    @Test
    void testLoginFail() throws Exception {

        System.out.println("Running testLoginFail");

        User user = new User();
        user.setUsername("admin");
        user.setPassword("wrong");

        when(service.login("admin", "wrong")).thenReturn(null);

        mockMvc.perform(post("/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }
}