package com.example.webstorebackend.product.service

import com.example.webstorebackend.common.exception.NotFoundException
import com.example.webstorebackend.product.dto.CartItemDTO
import com.example.webstorebackend.product.dto.CartResponseDTO
import com.example.webstorebackend.product.mapper.CartItemMapper
import com.example.webstorebackend.product.repository.*
import com.example.webstorebackend.product.entity.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CartService(
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
    private val cartItemRepository: CartItemRepository
) {

    @Transactional
    fun getOrCreateCart(userId: Long): Cart {
        val user = userRepository.findById(userId)
            .orElseThrow { NotFoundException("User with id $userId not found") }

        return cartRepository.findByUserId(userId) ?: cartRepository.save(Cart(user = user))
    }

    @Transactional
    fun getCart(userId: Long): CartResponseDTO {
        val cart = getOrCreateCart(userId)

        val itemDTOs = cart.items.map { CartItemMapper.toDetailDTO(it) }
        val totalPrice = itemDTOs.fold(BigDecimal.ZERO) { acc, item ->
            acc + (item.price * item.quantity.toBigDecimal())
        }

        return CartResponseDTO(
            userId = userId,
            items = itemDTOs,
            totalPrice = totalPrice
        )
    }

    @Transactional
    fun addItemToCart(userId: Long, dto: CartItemDTO) {
        val cart = getOrCreateCart(userId)
        val product = productRepository.findById(dto.productId)
            .orElseThrow { NotFoundException("Product with id ${dto.productId} not found") }

        val existingItem = cart.items.find { it.product.id == dto.productId }

        if (existingItem != null) {
            existingItem.quantity += dto.quantity
        } else {
            val newItem = CartItemMapper.toEntity(dto, cart, product)
            cart.items.add(newItem)
        }

        // Save wird durch Cascade automatisch übernommen, aber falls nötig:
        cartRepository.save(cart)
    }

    @Transactional
    fun removeItemFromCart(userId: Long, productId: Long) {
        val cart = getOrCreateCart(userId)
        val item = cart.items.find { it.product.id == productId }
            ?: throw NotFoundException("Item with product id $productId not found in cart")

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
            throw IllegalStateException("Cart is empty")
        }

        // Simulierter Checkout
        clearCart(userId)

        return "Checkout erfolgreich für User $userId"
    }
}
