package com.example.product.application.service;

import com.example.product.application.dto.ProductCreateCommand;
import com.example.product.application.dto.ProductResponse;
import com.example.product.application.port.in.CreateProductUseCase;
import com.example.product.application.port.in.GetProductQuery;
import com.example.product.application.port.out.ProductRepositoryPort;
import com.example.product.domain.exception.ProductNotFoundException;
import com.example.product.domain.model.Money;
import com.example.product.domain.model.Product;
import org.springframework.stereotype.Service;

/**
 * Service implementing both create and query use cases for products.
 * This acts as the application service layer coordinating domain logic.
 */
@Service
public class ProductService implements CreateProductUseCase, GetProductQuery {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ProductResponse createProduct(ProductCreateCommand command) {
        Money price = new Money(command.getPrice(), command.getCurrency());
        Product product = new Product(null, command.getName(), command.getDescription(), price);
        
        Product savedProduct = productRepositoryPort.save(product);
        
        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepositoryPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        
        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency()
        );
    }
}
