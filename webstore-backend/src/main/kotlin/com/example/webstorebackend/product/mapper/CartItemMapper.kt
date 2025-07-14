package com.example.webstorebackend.product.mapper

import com.example.webstorebackend.product.dto.CartItemDTO
import com.example.webstorebackend.product.dto.CartItemDetailDTO
import com.example.webstorebackend.product.entity.Cart
import com.example.webstorebackend.product.entity.CartItem
import com.example.webstorebackend.product.entity.Product
import org.springframework.stereotype.Component

@Component
object CartItemMapper {

    fun toEntity(dto: CartItemDTO, cart: Cart, product: Product): CartItem {
        return CartItem(
            cart = cart,
            product = product,
            quantity = dto.quantity
        )
    }

    fun toDetailDTO(cartItem: CartItem): CartItemDetailDTO {
        return CartItemDetailDTO(
            productId = cartItem.product.id,
            name = cartItem.product.name,
            price = cartItem.product.price,
            quantity = cartItem.quantity
        )
    }
}
