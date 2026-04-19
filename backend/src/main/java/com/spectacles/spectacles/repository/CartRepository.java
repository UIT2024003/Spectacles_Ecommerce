package com.spectacles.spectacles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spectacles.spectacles.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUsername(String username);
}