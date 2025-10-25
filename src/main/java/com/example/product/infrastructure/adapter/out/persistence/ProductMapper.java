package com.example.product.infrastructure.adapter.out.persistence;

import com.example.product.domain.model.Money;
import com.example.product.domain.model.Product;

/**
 * Mapper to convert between domain models and JPA entities.
 * This keeps the domain layer clean from persistence concerns.
 */
public class ProductMapper {

    public static ProductJpaEntity toJpaEntity(Product product) {
        return new ProductJpaEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency()
        );
    }

    public static Product toDomain(ProductJpaEntity entity) {
        Money price = new Money(entity.getPrice(), entity.getCurrency());
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                price
        );
    }
}
