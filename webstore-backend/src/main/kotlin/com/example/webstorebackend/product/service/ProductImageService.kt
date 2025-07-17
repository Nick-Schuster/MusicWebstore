package com.example.webstorebackend.product.service

import com.example.webstorebackend.common.exception.NotFoundException
import com.example.webstorebackend.product.dto.ProductImageRequestDTO
import com.example.webstorebackend.product.dto.ProductImageResponseDTO
import com.example.webstorebackend.product.entity.ProductImage
import com.example.webstorebackend.product.mapper.ProductImageMapper
import com.example.webstorebackend.product.repository.ProductImageRepository
import com.example.webstorebackend.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductImageService(
    private val productRepository: ProductRepository,
    private val imageRepository: ProductImageRepository
) {

    @Transactional(readOnly = true)
    fun getAllImages(productId: Long): List<ProductImageResponseDTO> {
        val product = productRepository.findById(productId)
            .orElseThrow { NotFoundException("Product with ID $productId not found.") }

        return product.images.map { ProductImageMapper.toImageDTO(it) }
    }

    @Transactional
    fun addImage(productId: Long, request: ProductImageRequestDTO): ProductImageResponseDTO {
        val product = productRepository.findById(productId)
            .orElseThrow { NotFoundException("Product with ID $productId not found.") }

        val image: ProductImage = ProductImageMapper.toImageEntity(request, product)
        val saved = imageRepository.save(image)

        return ProductImageMapper.toImageDTO(saved)
    }

    @Transactional
    fun deleteImage(productId: Long, imageId: Long) {
        val image = imageRepository.findById(imageId)
            .orElseThrow { NotFoundException("Image with ID $imageId not found.") }

        if (image.product.id != productId) {
            throw NotFoundException("Image with ID $imageId does not belong to product $productId.")
        }

        imageRepository.delete(image)
    }
}

