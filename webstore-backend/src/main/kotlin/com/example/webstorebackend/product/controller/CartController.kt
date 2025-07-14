package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.dto.CartItemDTO
import com.example.webstorebackend.product.dto.CartResponseDTO
import com.example.webstorebackend.product.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cart")
class CartController(
    private val cartService: CartService
) {

    @GetMapping("/{userId}")
    fun getCart(@PathVariable userId: Long): ResponseEntity<CartResponseDTO> {
        val cart = cartService.getCart(userId)
        return ResponseEntity.ok(cart)
    }

    @PostMapping("/{userId}")
    fun addItemToCart(
        @PathVariable userId: Long,
        @RequestBody dto: CartItemDTO
    ): ResponseEntity<Void> {
        cartService.addItemToCart(userId, dto)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{userId}/{productId}")
    fun removeItem(
        @PathVariable userId: Long,
        @PathVariable productId: Long
    ): ResponseEntity<Void> {
        cartService.removeItemFromCart(userId, productId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{userId}/checkout")
    fun checkout(@PathVariable userId: Long): ResponseEntity<String> {
        val confirmation = cartService.checkout(userId)
        return ResponseEntity.ok(confirmation)
    }
}
