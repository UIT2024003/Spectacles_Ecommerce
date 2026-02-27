package com.spectacles.spectacles.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.spectacles.spectacles.model.Product;
import com.spectacles.spectacles.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product save(Product p) {
        return repo.save(p);
    }
}