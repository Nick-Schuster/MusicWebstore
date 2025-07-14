package com.example.webstorebackend.product.mapper

import com.example.webstorebackend.product.dto.ProductReviewResponseDTO
import com.example.webstorebackend.product.entity.ProductReview
import org.springframework.stereotype.Component

@Component
object ProductReviewMapper {
    fun toDto(review: ProductReview): ProductReviewResponseDTO = ProductReviewResponseDTO(
        id = review.id,
        rating = review.rating,
        comment = review.comment
    )
}
