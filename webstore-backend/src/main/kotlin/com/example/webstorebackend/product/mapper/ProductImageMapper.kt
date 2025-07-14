package com.example.webstorebackend.product.mapper

import com.example.webstorebackend.product.dto.ProductImageRequestDTO
import com.example.webstorebackend.product.dto.ProductImageResponseDTO
import com.example.webstorebackend.product.entity.Product
import com.example.webstorebackend.product.entity.ProductImage
import org.springframework.stereotype.Component

@Component
object ProductImageMapper {

    // Image Mappings
    fun toImageEntity(dto: ProductImageRequestDTO, product: Product): com.example.webstorebackend.product.entity.ProductImage =
        ProductImage(
            imageUrl = dto.imageUrl,
            product = product
        )

    fun toImageDTO(image: ProductImage): ProductImageResponseDTO =
        ProductImageResponseDTO(
            id = image.id,
            imageUrl = image.imageUrl
        )
}