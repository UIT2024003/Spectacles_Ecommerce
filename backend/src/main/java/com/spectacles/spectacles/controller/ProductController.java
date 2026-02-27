package com.spectacles.spectacles.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.spectacles.spectacles.model.Product;
import com.spectacles.spectacles.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Product add(@RequestBody Product p) {
        return service.save(p);
    }
}