package com.example.webstorebackend.product.repository

import com.example.webstorebackend.product.entity.ProductReview
import org.springframework.data.jpa.repository.JpaRepository

interface ProductReviewRepository : JpaRepository<ProductReview, Long> {
    fun findByProductId(productId: Long): List<ProductReview>
}
