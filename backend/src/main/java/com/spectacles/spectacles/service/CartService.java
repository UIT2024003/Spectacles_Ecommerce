package com.spectacles.spectacles.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.spectacles.spectacles.model.Cart;
import com.spectacles.spectacles.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository repo;

    public CartService(CartRepository repo) {
        this.repo = repo;
    }

    // ✅ get cart items
    public List<Cart> getCart(String username) {
        return repo.findByUsername(username);
    }

    // ✅ add item
    public Cart addItem(Cart cart) {
        return repo.save(cart);
    }

    // ✅ remove item
    public void removeItem(Long id) {
        repo.deleteById(id);
    }
}