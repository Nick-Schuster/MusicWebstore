package com.example.webstorebackend.product.repository

import com.example.webstorebackend.product.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository

//not in use for now
interface CartRepository : JpaRepository<Cart, Long> {
    fun findByUserId(userId: Long): Cart?
}

