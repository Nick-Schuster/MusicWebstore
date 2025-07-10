package com.example.webstorebackend.product.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class ProductReviewRequestDTO(
    @field:NotNull
    @field:Min(1)
    @field:Max(5)
    val rating: Int,

    val comment: String? = null
)
