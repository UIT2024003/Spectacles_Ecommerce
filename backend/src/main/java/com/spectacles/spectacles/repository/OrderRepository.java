package com.spectacles.spectacles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spectacles.spectacles.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
}