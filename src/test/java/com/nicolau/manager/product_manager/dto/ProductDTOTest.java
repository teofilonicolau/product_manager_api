package com.nicolau.manager.product_manager.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidDTO() {
        ProductDTO productDTO = new ProductDTO("Product Valid", 50.0);
        var violations = validator.validate(productDTO);

        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidName() {
        ProductDTO productDTO = new ProductDTO("", 50.0);
        var violations = validator.validate(productDTO);

        assertEquals(1, violations.size());
        assertEquals("O nome deve ter entre 2 e 100 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidPrice() {
        ProductDTO productDTO = new ProductDTO("Valid Name", -10.0);
        var violations = validator.validate(productDTO);

        assertEquals(1, violations.size());
        assertEquals("O preço deve ser maior ou igual a zero", violations.iterator().next().getMessage());
    }
}
