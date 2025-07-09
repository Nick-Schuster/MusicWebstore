package com.example.webstorebackend.product.dto

import jakarta.validation.constraints.NotBlank

data class ProductImageDTO(
    @field:NotBlank
    val id: Long,
    val imageUrl: String
)