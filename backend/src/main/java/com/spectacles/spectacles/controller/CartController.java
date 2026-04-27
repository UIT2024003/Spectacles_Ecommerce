package com.spectacles.spectacles.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.spectacles.spectacles.model.Cart;
import com.spectacles.spectacles.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    // GET cart for user
    @GetMapping("/{username}")
    public List<Cart> getCart(@PathVariable String username) {
        return service.getCart(username);
    }

    // ADD item
    @PostMapping
    public Cart addItem(@RequestBody Cart cart) {
        return service.addItem(cart);
    }

    // DELETE item
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        service.removeItem(id);
    }
}