package com.example.product.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateValidProduct() {
        Money price = new Money(new BigDecimal("99.99"), "USD");
        Product product = new Product(1L, "Laptop", "A laptop", price);
        
        assertEquals(1L, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals("A laptop", product.getDescription());
        assertEquals(price, product.getPrice());
    }

    @Test
    void shouldThrowExceptionForNullName() {
        Money price = new Money(new BigDecimal("99.99"), "USD");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Product(1L, null, "Description", price));
    }

    @Test
    void shouldThrowExceptionForEmptyName() {
        Money price = new Money(new BigDecimal("99.99"), "USD");
        
        assertThrows(IllegalArgumentException.class, 
            () -> new Product(1L, "", "Description", price));
    }

    @Test
    void shouldThrowExceptionForNullPrice() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Product(1L, "Laptop", "Description", null));
    }
}
