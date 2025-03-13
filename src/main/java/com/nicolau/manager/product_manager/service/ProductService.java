package com.nicolau.manager.product_manager.service;

import com.nicolau.manager.product_manager.exception.ResourceNotFoundException;
import com.nicolau.manager.product_manager.model.Product;
import com.nicolau.manager.product_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() { return repository.findAll(); }
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Produto com ID " + id + " não encontrado.")
        );
    }

    public List<Product> findByName(String name) {
        return repository.findByNomeContainingIgnoreCase(name);
    }

    public List<Product> findByPriceRange(Double min, Double max) {
        return repository.findByPrecoBetween(min, max);
    }

    public Product save(Product product) { return repository.save(product); }
    public void delete(Long id) { repository.deleteById(id); }
}
