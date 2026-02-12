package com.product.service;

import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnList() {
        Product p1 = new Product(1L, "P1", "D1", 10.0, 5);
        Product p2 = new Product(2L, "P2", "D2", 20.0, 10);
        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnProduct() {
        Product p1 = new Product(1L, "P1", "D1", 10.0, 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(p1));

        Optional<Product> result = productService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("P1", result.get().getName());
    }

    @Test
    void save_ShouldReturnSavedProduct() {
        Product p1 = new Product(null, "P1", "D1", 10.0, 5);
        Product saved = new Product(1L, "P1", "D1", 10.0, 5);
        when(productRepository.save(p1)).thenReturn(saved);

        Product result = productService.save(p1);

        assertNotNull(result.getId());
        assertEquals(1L, result.getId());
    }

    @Test
    void searchProducts_ShouldReturnFilteredList() {
        Product p1 = new Product(1L, "Apple", "D1", 10.0, 5);
        when(productRepository.findByNameContainingIgnoreCase("app")).thenReturn(Arrays.asList(p1));

        List<Product> result = productService.searchProducts("app");

        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0).getName());
    }
}
