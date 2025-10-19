package com.example.product.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void shouldCreateValidMoney() {
        Money money = new Money(new BigDecimal("99.99"), "USD");
        
        assertEquals(new BigDecimal("99.99"), money.getAmount());
        assertEquals("USD", money.getCurrency());
    }

    @Test
    void shouldThrowExceptionForNullAmount() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Money(null, "USD"));
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Money(new BigDecimal("-10"), "USD"));
    }

    @Test
    void shouldThrowExceptionForNullCurrency() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Money(new BigDecimal("10"), null));
    }

    @Test
    void shouldThrowExceptionForEmptyCurrency() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Money(new BigDecimal("10"), ""));
    }

    @Test
    void shouldBeEqualWhenSameValue() {
        Money money1 = new Money(new BigDecimal("99.99"), "USD");
        Money money2 = new Money(new BigDecimal("99.99"), "USD");
        
        assertEquals(money1, money2);
    }
}
