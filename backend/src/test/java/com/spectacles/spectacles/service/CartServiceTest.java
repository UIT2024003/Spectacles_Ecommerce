package com.spectacles.spectacles.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spectacles.spectacles.model.Cart;
import com.spectacles.spectacles.repository.CartRepository;

class CartServiceTest {

    @Mock
    private CartRepository repo;

    @InjectMocks
    private CartService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCart() {
        Cart c = new Cart();
        c.setUsername("user1");

        when(repo.findByUsername("user1")).thenReturn(List.of(c));

        List<Cart> result = service.getCart("user1");

        assertEquals(1, result.size());
        verify(repo).findByUsername("user1");
    }

    @Test
    void testAddItem() {
        Cart c = new Cart();
        c.setUsername("user1");

        when(repo.save(c)).thenReturn(c);

        Cart result = service.addItem(c);

        assertNotNull(result);
        verify(repo).save(c);
    }

    @Test
    void testRemoveItem() {
        service.removeItem(1L);
        verify(repo).deleteById(1L);
    }
}