package com.example.product.application.dto;

import java.math.BigDecimal;

/**
 * Command DTO for creating a product.
 * Commands represent requests to change the system's state.
 */
public class ProductCreateCommand {
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;

    public ProductCreateCommand() {
    }

    public ProductCreateCommand(String name, String description, BigDecimal price, String currency) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
