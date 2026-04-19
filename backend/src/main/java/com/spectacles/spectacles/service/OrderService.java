package com.spectacles.spectacles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spectacles.spectacles.model.Order;
import com.spectacles.spectacles.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    // ✅ place order
    public Order placeOrder(Order order) {
        return repo.save(order);
    }

    // ✅ get user orders
    public List<Order> getOrders(String username) {
        return repo.findByUsername(username);
    }
}