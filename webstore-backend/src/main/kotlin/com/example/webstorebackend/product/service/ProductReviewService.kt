package com.example.webstorebackend.product.service

import com.example.webstorebackend.product.dto.ProductReviewResponseDTO
import com.example.webstorebackend.product.dto.ProductReviewRequestDTO
import com.example.webstorebackend.product.entity.Product
import com.example.webstorebackend.product.entity.ProductReview
import com.example.webstorebackend.product.mapper.ProductReviewMapper
import com.example.webstorebackend.product.repository.ProductRepository
import com.example.webstorebackend.product.repository.ProductReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductReviewService(
    private val productRepository: ProductRepository,
    private val reviewRepository: ProductReviewRepository
) {

    fun getReviewsForProduct(productId: Long): List<ProductReviewResponseDTO> {
        return reviewRepository.findByProductId(productId)
            .map { ProductReviewMapper.toDto(it) }
    }

    @Transactional
    fun addReview(productId: Long, dto: ProductReviewRequestDTO): ProductReviewResponseDTO {
        val product = productRepository.findById(productId)
            .orElseThrow { RuntimeException("Product not found") }

        val review = ProductReview(
            rating = dto.rating,
            comment = dto.comment,
            product = product
        )

        val saved = reviewRepository.save(review)
        updateAverageRating(product)

        return ProductReviewMapper.toDto(saved)
    }

    private fun updateAverageRating(product: Product) {
        val reviews = reviewRepository.findByProductId(product.id)
        val average = reviews.map { it.rating }.average()
        product.averageRating = average
        productRepository.save(product)
    }
}
