package com.example.webstorebackend.product.mapper

import com.example.webstorebackend.product.dto.ProductImageDTO
import com.example.webstorebackend.product.entity.Product
import com.example.webstorebackend.product.entity.ProductImage

object ProductImageMapper {

    // Image Mappings
    fun toImageEntity(dto: ProductImageDTO, product: Product): com.example.webstorebackend.product.entity.ProductImage =
        ProductImage(
            imageUrl = dto.imageUrl,
            product = product
        )

    fun toImageDTO(image: ProductImage): ProductImageDTO =
        ProductImageDTO(
            id = image.id,
            imageUrl = image.imageUrl
        )
}