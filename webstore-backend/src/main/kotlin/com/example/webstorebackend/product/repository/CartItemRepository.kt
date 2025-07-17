package com.example.webstorebackend.product.repository

import com.example.webstorebackend.product.entity.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//not in use for now
@Repository
interface CartItemRepository : JpaRepository<CartItem, Long> {
    fun deleteAllByCartId(cartId: Long)
}
