package com.example.webstorebackend.product.dto

import java.math.BigDecimal

data class CartItemDTO(
    val productId: Long,
    val quantity: Int
)

data class CartResponseDTO(
    val userId: Long,
    val items: List<CartItemDetailDTO>,
    val totalPrice: BigDecimal
)

data class CartItemDetailDTO(
    val productId: Long,
    val name: String,
    val price: BigDecimal,
    val quantity: Int
)
