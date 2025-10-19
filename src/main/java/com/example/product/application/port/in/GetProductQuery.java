package com.example.product.application.port.in;

import com.example.product.application.dto.ProductResponse;

/**
 * Input port (query) for retrieving a product.
 * Queries are read-only operations that don't modify the system's state.
 */
public interface GetProductQuery {
    ProductResponse getProduct(Long id);
}
