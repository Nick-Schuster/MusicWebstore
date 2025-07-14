package com.example.webstorebackend.product.dto

data class UserResponseDTO (
    val id: Long,
    val username: String,
    val name: String,
    val admin: Boolean
)
