package com.gchub.producthub.service;

import com.gchub.producthub.dto.ProductRequest;
import com.gchub.producthub.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Product create(ProductRequest request) {
        Product product = Product.builder()
                .id(counter.incrementAndGet())
                .sku(request.sku())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .status("ACTIVE")
                .stockQuantity(request.stockQuantity())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        products.add(product);
        return product;
    }

    public Optional<Product> update(Long id, ProductRequest request) {
        return findById(id).map(product -> {
            product.setName(request.name());
            product.setSku(request.sku());
            product.setDescription(request.description());
            product.setPrice(request.price());
            product.setStockQuantity(request.stockQuantity());
            product.setUpdatedAt(LocalDateTime.now());
            return product;
        });
    }

    public boolean delete(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}