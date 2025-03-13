package com.nicolau.manager.product_manager.repository;

import com.nicolau.manager.product_manager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNomeContainingIgnoreCase(String nome);

    List<Product> findByPrecoBetween(Double min, Double max);




}