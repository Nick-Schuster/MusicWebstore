package com.example.webstorebackend.product.dto

data class ProductReviewResponseDTO(
    val id: Long = 0,
    val rating: Int,
    val comment: String? = null
)
