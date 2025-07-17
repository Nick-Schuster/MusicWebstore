package com.example.webstorebackend.product.dto

//not in use for now
data class UserRequestDTO(
    val username: String,
    val password: String,
    val name: String,
    val admin: Boolean = false
)