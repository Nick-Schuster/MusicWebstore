package com.example.webstorebackend.product.service

import com.example.webstorebackend.product.dto.ProductImageDTO
import com.example.webstorebackend.product.mapper.ProductImageMapper
import com.example.webstorebackend.product.repository.ProductImageRepository
import com.example.webstorebackend.product.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class ProductImageService(
    private val productRepository: ProductRepository,
    private val imageRepository: ProductImageRepository
) {
    fun getAllImages(productId: Long): List<ProductImageDTO> {
        val product = productRepository.findById(productId)
            .orElseThrow { EntityNotFoundException("Product not found") }

        return product.images.map { ProductImageMapper.toImageDTO(it) }
    }

    fun addImage(productId: Long, dto: ProductImageDTO): ProductImageDTO {
        val product = productRepository.findById(productId)
            .orElseThrow { EntityNotFoundException("Product not found") }

        val image = ProductImageMapper.toImageEntity(dto, product)
        return ProductImageMapper.toImageDTO(imageRepository.save(image))
    }

    fun deleteImage(productId: Long, imageId: Long): Boolean {
        val image = imageRepository.findById(imageId).orElse(null) ?: return false

        return if (image.product.id == productId) {
            imageRepository.delete(image)
            true
        } else false
    }
}
