package com.spectacles.spectacles.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.spectacles.spectacles.model.Order;
import com.spectacles.spectacles.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // place order
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return service.placeOrder(order);
    }

    // get user orders
    @GetMapping("/{username}")
    public List<Order> getOrders(@PathVariable String username) {
        return service.getOrders(username);
    }
}