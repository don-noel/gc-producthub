package com.gchub.producthub.service;

import com.gchub.producthub.dto.ProductRequest;
import com.gchub.producthub.model.Product;
import com.gchub.producthub.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product create(ProductRequest request) {
        Product product = Product.builder()
                .sku(request.sku())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .status("ACTIVE")
                .stockQuantity(request.stockQuantity())
                .build();
        return productRepository.save(product);
    }

    public Optional<Product> update(Long id, ProductRequest request) {
        return productRepository.findById(id).map(product -> {
            product.setName(request.name());
            product.setSku(request.sku());
            product.setDescription(request.description());
            product.setPrice(request.price());
            product.setStockQuantity(request.stockQuantity());
            return productRepository.save(product);
        });
    }

    public boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}