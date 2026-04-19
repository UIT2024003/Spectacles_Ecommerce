package com.spectacles.spectacles.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spectacles.spectacles.model.Order;
import com.spectacles.spectacles.repository.OrderRepository;

class OrderServiceTest {

    @Mock
    private OrderRepository repo;

    @InjectMocks
    private OrderService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder() {
        Order order = new Order();
        order.setUsername("user1");
        order.setTotal(1000);

        when(repo.save(order)).thenReturn(order);

        Order result = service.placeOrder(order);

        assertNotNull(result);
        assertEquals("user1", result.getUsername());
        verify(repo).save(order);
    }

    @Test
    void testGetOrders() {
        Order order = new Order();
        order.setUsername("user1");

        when(repo.findByUsername("user1")).thenReturn(List.of(order));

        List<Order> result = service.getOrders("user1");

        assertEquals(1, result.size());
        verify(repo).findByUsername("user1");
    }
}