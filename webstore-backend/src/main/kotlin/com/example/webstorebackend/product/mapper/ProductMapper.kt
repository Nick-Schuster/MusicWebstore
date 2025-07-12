package com.example.webstorebackend.product.mapper

import com.example.webstorebackend.product.dto.*
import com.example.webstorebackend.product.entity.Product

object ProductMapper {

    //Product Mappings
    fun toProductEntity(dto: ProductRequestDTO): Product = Product(
        name = dto.name,
        description = dto.description,
        price = dto.price,
        inStock = dto.inStock
    )

    fun toProductDto(product: Product): ProductResponseDTO = ProductResponseDTO(
        id = product.id,
        name = product.name,
        description = product.description,
        price = product.price,
        inStock = product.inStock,
        averageRating = product.averageRating,
        images = product.images.map { ProductImageResponseDTO(it.id, it.imageUrl) }
    )

}