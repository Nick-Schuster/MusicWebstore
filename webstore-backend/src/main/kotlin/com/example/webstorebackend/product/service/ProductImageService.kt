package com.example.webstorebackend.product.service

import com.example.webstorebackend.product.dto.ProductImageDTO
import com.example.webstorebackend.product.mapper.ProductImageMapper
import com.example.webstorebackend.product.repository.ProductImageRepository
import com.example.webstorebackend.product.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import com.example.webstorebackend.common.exception.NotFoundException

@Service
class ProductImageService(
    private val productRepository: ProductRepository,
    private val imageRepository: ProductImageRepository
) {
    fun getAllImages(productId: Long): List<ProductImageDTO> {
        val product = productRepository.findById(productId)
            .orElseThrow { NotFoundException("Product with id $productId not found") }

        return product.images.map { ProductImageMapper.toImageDTO(it) }
    }

    fun addImage(productId: Long, dto: ProductImageDTO): ProductImageDTO {
        val product = productRepository.findById(productId)
            .orElseThrow { NotFoundException("Product with id $productId not found") }

        val image = ProductImageMapper.toImageEntity(dto, product)
        return ProductImageMapper.toImageDTO(imageRepository.save(image))
    }

    fun deleteImage(productId: Long, imageId: Long): Boolean {
        val image = imageRepository.findById(imageId)
            .orElseThrow { NotFoundException("Product with id $productId not found") }

        return if (image.product.id == productId) {
            imageRepository.delete(image)
            true
        } else false
    }
}
