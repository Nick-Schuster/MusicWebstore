package com.example.webstorebackend.product.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductRequestDTO(
    @field:NotBlank
    val name: String,

    val description: String? = null,

    @field:NotNull
    @field:DecimalMin("0.0", inclusive = false)
    val price: BigDecimal,

    val inStock: Boolean = true
)