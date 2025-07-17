package com.example.webstorebackend.product.service

import com.example.webstorebackend.common.exception.NotFoundException
import com.example.webstorebackend.product.dto.AddToCartRequestDTO
import com.example.webstorebackend.product.dto.CartDTO
import com.example.webstorebackend.product.mapper.CartItemMapper
import com.example.webstorebackend.product.repository.*
import com.example.webstorebackend.product.entity.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartService(
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
    private val cartItemRepository: CartItemRepository
) {

    @Transactional(readOnly = true)
    fun getOrCreateCart(userId: Long): Cart {
        val user = userRepository.findById(userId)
            .orElseThrow { NotFoundException("User with ID $userId not found.") }

        return cartRepository.findByUserId(userId) ?: cartRepository.save(Cart(user = user))
    }

    @Transactional(readOnly = true)
    fun getCart(userId: Long): CartDTO {
        val cart = getOrCreateCart(userId)
        val items = cart.items.map { CartItemMapper.toDTO(it) }
        return CartDTO(id = cart.id, userId = cart.user.id, items = items)
    }

    @Transactional
    fun addItemToCart(userId: Long, request: AddToCartRequestDTO) {
        val cart = getOrCreateCart(userId)
        val product = productRepository.findById(request.productId)
            .orElseThrow { NotFoundException("Product with ID ${request.productId} not found.") }

        val existingItem = cart.items.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += request.quantity
        } else {
            val newItem = CartItemMapper.toEntity(request, cart, product)
            cart.items.add(newItem)
        }
        cartRepository.save(cart)
    }

    @Transactional
    fun removeItemFromCart(userId: Long, productId: Long) {
        val cart = getOrCreateCart(userId)
        val item = cart.items.find { it.product.id == productId }
            ?: throw NotFoundException("No cart item with product ID $productId found for user $userId.")
        cart.items.remove(item)
        cartItemRepository.delete(item)
    }

    @Transactional
    fun clearCart(userId: Long) {
        val cart = getOrCreateCart(userId)
        cart.items.clear()
        cartItemRepository.deleteAllByCartId(cart.id)
    }

    @Transactional
    fun checkout(userId: Long): String {
        val cart = getOrCreateCart(userId)
        if (cart.items.isEmpty()) {
            throw IllegalStateException("Cannot checkout an empty cart.")
        }
        clearCart(userId)
        return "Checkout completed for user ID $userId"
    }
}


