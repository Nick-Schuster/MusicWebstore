package com.example.webstorebackend.product.dto

import jakarta.validation.constraints.NotBlank

data class ProductImageRequestDTO(
    @field:NotBlank
    val imageUrl: String
)