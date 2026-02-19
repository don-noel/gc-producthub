package com.gchub.producthub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

    @NotBlank(message = "Le SKU est obligatoire")
    String sku,

    @NotBlank(message = "Le nom est obligatoire")
    String name,

    String description,

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    BigDecimal price,

    @NotNull(message = "La quantité est obligatoire")
    Integer stockQuantity
) {}