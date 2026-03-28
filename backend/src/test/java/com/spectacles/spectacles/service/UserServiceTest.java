package com.spectacles.spectacles.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.spectacles.spectacles.model.User;
import com.spectacles.spectacles.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ TEST 1: SUCCESS LOGIN
    @Test
    void testLoginSuccess() {

        System.out.println("Running testLoginSuccess");

        // fake user (mock data)
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin123");

        // mock repo behavior
        when(repo.findByUsername("admin")).thenReturn(user);

        // call method
        User result = service.login("admin", "admin123");

        // assertions
        assertNotNull(result);
        assertEquals("admin", result.getUsername());
    }

    // ❌ TEST 2: WRONG PASSWORD
    @Test
    void testLoginWrongPassword() {

        System.out.println("Running testLoginWrongPassword");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin123");

        when(repo.findByUsername("admin")).thenReturn(user);

        User result = service.login("admin", "wrong");

        assertNull(result);
    }

    // ❌ TEST 3: USER NOT FOUND
    @Test
    void testLoginUserNotFound() {
        System.out.println("Running testLoginUserNotFound");

        when(repo.findByUsername("admin")).thenReturn(null);

        User result = service.login("admin", "admin123");

        assertNull(result);
    }
}