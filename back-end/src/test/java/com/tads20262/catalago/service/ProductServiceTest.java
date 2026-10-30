package com.tads20262.catalago.service;

import com.tads20262.catalago.dto.ProductDTO;
import com.tads20262.catalago.entity.Product;
import com.tads20262.catalago.repository.CategoryRepository;
import com.tads20262.catalago.repository.ProductRepository;
import com.tads20262.catalago.service.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findById_returnsProductDto_whenProductExisrs() {
        Product product = new Product();
        product.setId(10L);
        product.setName("Teclado");

        when(productRepository.findById(10L))
                .thenReturn(Optional.of(product));

        ProductDTO result = productService.findById(10L);

        assertEquals(10L, result.getId());
        assertEquals("Teclado", result.getName());
        verify(productRepository).findById(10L);
    }

    @Test
    void findById_thorowsResourceNotFound_whenProductDoesNotExeist(){

        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> productService.findById(99L)
        );

        verify(productRepository).findById(99L);
    }


}