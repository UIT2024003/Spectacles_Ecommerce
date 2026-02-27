package com.spectacles.spectacles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spectacles.spectacles.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}