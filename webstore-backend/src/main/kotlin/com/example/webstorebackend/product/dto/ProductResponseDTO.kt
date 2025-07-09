package com.example.webstorebackend.product.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductResponseDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val inStock: Boolean,
    val images: List<ProductImageDTO> = emptyList()
)