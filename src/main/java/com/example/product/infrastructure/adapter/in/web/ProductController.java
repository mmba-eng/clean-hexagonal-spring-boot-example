package com.example.product.infrastructure.adapter.in.web;

import com.example.product.application.dto.ProductCreateCommand;
import com.example.product.application.dto.ProductResponse;
import com.example.product.application.port.in.CreateProductUseCase;
import com.example.product.application.port.in.GetProductQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for product operations.
 * This is an input adapter that translates HTTP requests into application commands/queries.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductQuery getProductQuery;

    public ProductController(CreateProductUseCase createProductUseCase, GetProductQuery getProductQuery) {
        this.createProductUseCase = createProductUseCase;
        this.getProductQuery = getProductQuery;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateCommand command) {
        ProductResponse response = createProductUseCase.createProduct(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        ProductResponse response = getProductQuery.getProduct(id);
        return ResponseEntity.ok(response);
    }
}
