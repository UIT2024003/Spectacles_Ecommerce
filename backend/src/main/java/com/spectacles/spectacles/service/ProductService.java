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

    // ✅ GET ALL
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // ✅ ADD
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    // ✅ GET BY ID
    public Product getProductById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // ✅ UPDATE
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setBrand(updatedProduct.getBrand());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setImageUrl(updatedProduct.getImageUrl());
        existing.setDescription(updatedProduct.getDescription());
        existing.setStock(updatedProduct.getStock());

        return repo.save(existing);
    }

    // ✅ DELETE
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}