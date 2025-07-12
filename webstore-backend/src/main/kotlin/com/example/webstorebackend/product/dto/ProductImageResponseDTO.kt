package com.example.webstorebackend.product.dto

import jakarta.validation.constraints.NotBlank

data class ProductImageResponseDTO(
    val id: Long,
    val imageUrl: String
)