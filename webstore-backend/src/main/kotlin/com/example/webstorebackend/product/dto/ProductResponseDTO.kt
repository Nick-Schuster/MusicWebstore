package com.example.webstorebackend.product.dto

import java.math.BigDecimal

data class ProductResponseDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val inStock: Boolean,
    val averageRating: Double,
    val images: List<ProductImageResponseDTO> = emptyList()
)