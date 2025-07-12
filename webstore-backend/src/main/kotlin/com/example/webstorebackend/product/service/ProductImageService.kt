package com.example.webstorebackend.product.service

import com.example.webstorebackend.product.dto.ProductImageResponseDTO
import com.example.webstorebackend.product.mapper.ProductImageMapper
import com.example.webstorebackend.product.repository.ProductImageRepository
import com.example.webstorebackend.product.repository.ProductRepository
import org.springframework.stereotype.Service
import com.example.webstorebackend.common.exception.NotFoundException
import com.example.webstorebackend.product.dto.ProductImageRequestDTO

@Service
class ProductImageService(
    private val productRepository: ProductRepository,
    private val imageRepository: ProductImageRepository
) {
    fun getAllImages(productId: Long): List<ProductImageResponseDTO> {
        val product = productRepository.findById(productId)
            .orElseThrow { NotFoundException("Product with id $productId not found") }

        return product.images.map { ProductImageMapper.toImageDTO(it) }
    }

    fun addImage(productId: Long, dto: ProductImageRequestDTO): ProductImageResponseDTO {
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
