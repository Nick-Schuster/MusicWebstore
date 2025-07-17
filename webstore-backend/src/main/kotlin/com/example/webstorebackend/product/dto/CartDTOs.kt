package com.example.webstorebackend.product.dto

//not in use for now
data class AddToCartRequestDTO(
    val productId: Long,
    val quantity: Int
)

data class CartItemDTO(
    val id: Long,
    val productId: Long,
    val quantity: Int
)

data class CartDTO(
    val id: Long,
    val userId: Long,
    val items: List<CartItemDTO>
)

