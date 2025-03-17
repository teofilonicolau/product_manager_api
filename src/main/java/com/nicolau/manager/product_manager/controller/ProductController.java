package com.nicolau.manager.product_manager.controller;

import com.nicolau.manager.product_manager.dto.ProductDTO;
import com.nicolau.manager.product_manager.model.Product;
import com.nicolau.manager.product_manager.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) { return service.findById(id); }

    @GetMapping("/search")
    public List<Product> getByName(@RequestParam String name) { return service.findByName(name); }

    @GetMapping("/range")
    public List<Product> getByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return service.findByPriceRange(min, max);
    }

    @PostMapping
    public Product create(@Valid @RequestBody ProductDTO productDTO) {
        System.out.println("Produto recebido: " + productDTO.getNome() + ", preço: " + productDTO.getPreco());
        Product product = new Product();
        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        return service.save(product);
    }


    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setId(id); // Mapeia o ID recebido na requisição
        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        return service.save(product);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}