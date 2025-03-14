package com.nicolau.manager.product_manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolau.manager.product_manager.config.SecurityConfig;
import com.nicolau.manager.product_manager.dto.ProductDTO;
import com.nicolau.manager.product_manager.model.Product;
import com.nicolau.manager.product_manager.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ProductController.class)
@Import(SecurityConfig.class) // Apenas se a classe SecurityConfig for relevante
@WithMockUser // Cria um usuário mockado para passar pela autenticação
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service; // Mock do ProductService para evitar erro de dependência

    @Test
    void testGetAll() throws Exception {
        // Simula a resposta do ProductService
        List<Product> products = List.of(
                new Product(1L, "Product 1", 10.0),
                new Product(2L, "Product 2", 20.0)
        );
        when(service.findAll()).thenReturn(products);

        // Realiza uma requisição GET e valida o resultado
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Product 1"))
                .andExpect(jsonPath("$[1].preco").value(20.0));
    }

    @Test
    void testCreateProduct() throws Exception {
        // Dados de entrada e simulação do ProductService
        ProductDTO productDTO = new ProductDTO("Product Test", 30.0);
        Product savedProduct = new Product(1L, "Product Test", 30.0);
        when(service.save(any(Product.class))).thenReturn(savedProduct);

        // Realiza uma requisição POST e valida o resultado
        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Product Test"));
    }
}
