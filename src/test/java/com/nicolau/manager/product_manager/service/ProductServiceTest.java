package com.nicolau.manager.product_manager.service;

import com.nicolau.manager.product_manager.exception.ResourceNotFoundException;
import com.nicolau.manager.product_manager.model.Product;
import com.nicolau.manager.product_manager.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void testFindAll() {
        List<Product> products = List.of(new Product(1L, "Product 1", 10.0), new Product(2L, "Product 2", 20.0));
        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_ExistingId() {
        Product product = new Product(1L, "Product 1", 10.0);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Product result = service.findById(1L);

        assertEquals("Product 1", result.getNome());
        assertEquals(10.0, result.getPreco());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NonExistingId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(1L);
        });

        assertEquals("Produto com ID 1 não encontrado.", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        Product product = new Product(null, "Product 1", 10.0);
        Product savedProduct = new Product(1L, "Product 1", 10.0);

        when(repository.save(product)).thenReturn(savedProduct);

        Product result = service.save(product);

        assertEquals(1L, result.getId());
        assertEquals("Product 1", result.getNome());
        assertEquals(10.0, result.getPreco());
        verify(repository, times(1)).save(product);
    }

    @Test
    void testDelete_ExistingId() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NonExistingId() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(1L);

        EmptyResultDataAccessException exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            service.delete(1L);
        });

        verify(repository, times(1)).deleteById(1L);
    }
}
