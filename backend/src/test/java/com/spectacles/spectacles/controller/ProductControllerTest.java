package com.spectacles.spectacles.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spectacles.spectacles.model.Product;
import com.spectacles.spectacles.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ TEST GET ALL PRODUCTS
    @Test
    void testGetAllProducts() throws Exception {

        System.out.println("Running testGetAllProducts Controller");

        Product p1 = new Product();
        p1.setName("RayBan Aviator");
        p1.setBrand("RayBan");
        p1.setPrice(4999);
        p1.setCategory("Sunglasses");
        p1.setImageUrl("https://example.com/rayban.jpg");
        p1.setDescription("Classic aviator sunglasses");
        p1.setStock(10);

        Product p2 = new Product();
        p2.setName("Lenskart Blue Light");
        p2.setBrand("Lenskart");
        p2.setPrice(1999);
        p2.setCategory("Computer Glasses");
        p2.setImageUrl("https://example.com/lenskart.jpg");
        p2.setDescription("Blue light blocking spectacles");
        p2.setStock(20);

        List<Product> products = Arrays.asList(p1, p2);

        when(service.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("RayBan Aviator"))
                .andExpect(jsonPath("$[1].brand").value("Lenskart"));
    }

    // ✅ TEST ADD PRODUCT
    @Test
    void testAddProduct() throws Exception {

        System.out.println("Running testAddProduct Controller");

        Product product = new Product();
        product.setName("Oakley Sport");
        product.setBrand("Oakley");
        product.setPrice(3499);
        product.setCategory("Sports Glasses");
        product.setImageUrl("https://example.com/oakley.jpg");
        product.setDescription("Sports spectacles");
        product.setStock(15);

        when(service.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Oakley Sport"))
                .andExpect(jsonPath("$.brand").value("Oakley"));
    }
}