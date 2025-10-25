package com.example.product.application.port.out;

import com.example.product.domain.model.Product;

import java.util.Optional;

/**
 * Output port for product repository.
 * Ports define interfaces that the application layer depends on,
 * but are implemented by the infrastructure layer (Dependency Inversion).
 */
public interface ProductRepositoryPort {
    Product save(Product product);
    
    Optional<Product> findById(Long id);
}
