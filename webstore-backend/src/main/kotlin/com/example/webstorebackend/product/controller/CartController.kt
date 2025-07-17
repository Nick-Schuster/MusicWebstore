package com.example.webstorebackend.product.controller

import com.example.webstorebackend.product.dto.AddToCartRequestDTO
import com.example.webstorebackend.product.dto.CartDTO
import com.example.webstorebackend.product.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST controller for cart operations:
 * add, view, and remove products from a user's cart.
 */
//not in use for now
@RestController
@RequestMapping("/api/cart")
class CartController(
    private val cartService: CartService
) {

    @GetMapping("/{userId}")
    fun getCart(@PathVariable userId: Long): ResponseEntity<CartDTO> {
        val cart = cartService.getCart(userId)
        return ResponseEntity.ok(cart)
    }

    @PostMapping("/{userId}")
    fun addItemToCart(
        @PathVariable userId: Long,
        @RequestBody dto: AddToCartRequestDTO
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

