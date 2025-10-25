package com.example.product.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for ProductJpaEntity.
 */
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
}
