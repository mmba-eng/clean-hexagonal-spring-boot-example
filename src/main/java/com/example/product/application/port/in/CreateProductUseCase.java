package com.example.product.application.port.in;

import com.example.product.application.dto.ProductCreateCommand;
import com.example.product.application.dto.ProductResponse;

/**
 * Input port (use case) for creating a product.
 * Use cases define the application's business logic and orchestrate
 * the flow of data to and from the domain entities.
 */
public interface CreateProductUseCase {
    ProductResponse createProduct(ProductCreateCommand command);
}
