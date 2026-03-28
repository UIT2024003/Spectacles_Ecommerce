package com.spectacles.spectacles.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.spectacles.spectacles.model.Product;
import com.spectacles.spectacles.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ TEST 1: GET ALL PRODUCTS
    @Test
    void testGetAllProducts() {

        System.out.println("Running testGetAllProducts");

        List<Product> list = new ArrayList<>();
        list.add(new Product());
        list.add(new Product());

        when(repo.findAll()).thenReturn(list);

        List<Product> result = service.getAllProducts();

        assertEquals(2, result.size());
    }

    // ✅ TEST 2: ADD PRODUCT
    @Test
    void testAddProduct() {

        System.out.println("Running testAddProduct");

        Product product = new Product();
        product.setName("Specs");

        when(repo.save(product)).thenReturn(product);

        Product result = service.addProduct(product);

        assertEquals("Specs", result.getName());
    }

    // ✅ TEST 3: GET PRODUCT BY ID
    @Test
    void testGetProductById() {

        System.out.println("Running testGetProductById");

        Product product = new Product();

        when(repo.findById(1L)).thenReturn(Optional.of(product));

        Product result = service.getProductById(1L);

        assertNotNull(result);
    }

    // ✅ TEST 4: DELETE PRODUCT
    @Test
    void testDeleteProduct() {

        System.out.println("Running testDeleteProduct");

        doNothing().when(repo).deleteById(1L);

        service.deleteProduct(1L);

        verify(repo, times(1)).deleteById(1L);
    }
}