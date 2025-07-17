package com.example.webstorebackend.product.mapper

import com.example.webstorebackend.product.dto.AddToCartRequestDTO
import com.example.webstorebackend.product.dto.CartItemDTO
import com.example.webstorebackend.product.entity.Cart
import com.example.webstorebackend.product.entity.CartItem
import com.example.webstorebackend.product.entity.Product
import org.springframework.stereotype.Component

//not in use for now
@Component
object CartItemMapper {

    fun toEntity(dto: AddToCartRequestDTO, cart: Cart, product: Product): CartItem {
        return CartItem(
            cart = cart,
            product = product,
            quantity = dto.quantity
        )
    }

    fun toDTO(entity: CartItem): CartItemDTO {
        return CartItemDTO(
            id = entity.id,
            productId = entity.product.id,
            quantity = entity.quantity
        )
    }
}
