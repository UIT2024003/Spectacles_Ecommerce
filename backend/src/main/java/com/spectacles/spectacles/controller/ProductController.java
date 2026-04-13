package com.spectacles.spectacles.controller;

import java.util.List;
import java.io.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spectacles.spectacles.model.Product;
import com.spectacles.spectacles.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ GET ALL PRODUCTS
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ✅ ADD PRODUCT
    /* @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    } */

    @PostMapping("/upload")
    public Product addProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("brand") String brand,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("stock") int stock
    ) {
        try {

            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File is missing!");
            }

            String fileName = file.getOriginalFilename();

            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            file.transferTo(new File(uploadDir + fileName));

            Product product = new Product();
            product.setName(name);
            product.setBrand(brand);
            product.setPrice(price);
            product.setCategory(category);
            product.setDescription(description);
            product.setStock(stock);
            product.setImageUrl(fileName);

            return productService.addProduct(product);

        } catch (Exception e) {
            e.printStackTrace(); // VERY IMPORTANT
            throw new RuntimeException(e);
        }
    }

    // ✅ GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // ✅ UPDATE PRODUCT
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // ✅ DELETE PRODUCT
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}