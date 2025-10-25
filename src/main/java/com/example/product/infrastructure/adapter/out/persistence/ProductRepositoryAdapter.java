package com.example.product.infrastructure.adapter.out.persistence;

import com.example.product.application.port.out.ProductRepositoryPort;
import com.example.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter implementing the ProductRepositoryPort using JPA.
 * This is an output adapter that implements the port defined in the application layer.
 */
@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = ProductMapper.toJpaEntity(product);
        ProductJpaEntity savedEntity = productJpaRepository.save(entity);
        return ProductMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id)
                .map(ProductMapper::toDomain);
    }
}
