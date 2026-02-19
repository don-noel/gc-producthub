package com.gchub.producthub.service;

import com.gchub.producthub.dto.PageResponse;
import com.gchub.producthub.dto.ProductRequest;
import com.gchub.producthub.dto.ProductResponse;
import com.gchub.producthub.model.Product;
import com.gchub.producthub.repository.ProductRepository;
import com.gchub.producthub.repository.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PageResponse<ProductResponse> findAll(
            String name,
            String status,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            int page,
            int size,
            String sortBy,
            String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductResponse> result = productRepository
                .findAll(ProductSpecification.withFilters(name, status, minPrice, maxPrice), pageable)
                .map(ProductResponse::from);

        return PageResponse.from(result);
    }

    public Optional<ProductResponse> findById(Long id) {
        return productRepository.findById(id).map(ProductResponse::from);
    }

    public ProductResponse create(ProductRequest request) {
        Product product = Product.builder()
                .sku(request.sku())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .status("ACTIVE")
                .stockQuantity(request.stockQuantity())
                .build();
        return ProductResponse.from(productRepository.save(product));
    }

    public Optional<ProductResponse> update(Long id, ProductRequest request) {
        return productRepository.findById(id).map(product -> {
            product.setName(request.name());
            product.setSku(request.sku());
            product.setDescription(request.description());
            product.setPrice(request.price());
            product.setStockQuantity(request.stockQuantity());
            return ProductResponse.from(productRepository.save(product));
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